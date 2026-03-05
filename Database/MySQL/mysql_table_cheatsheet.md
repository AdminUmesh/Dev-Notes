
# MySQL Table & Data Types — Practical Cheat Sheet (Downloadable)

Author: Umesh Kumar Singh (Generated notes)  
Purpose: Practical reference covering data types, table creation, constraints, ALTER operations, common pitfalls, and industry best practices.

---

## Quick environment note (recommended)
- Use **MySQL 8.x** or **MariaDB 10.x**
- Default engine for foreign keys: **InnoDB**
- Default charset: **utf8mb4** (supports emojis)
```sql
-- recommended server defaults (optional)
SET GLOBAL innodb_file_per_table=1;
```

---

## 1. Common Data Types (Industry guidance)

### Date / Time types
- `DATE` — Stores **date only** (`YYYY-MM-DD`). Use for birthdays, due dates.
- `DATETIME` — Stores date and time **without timezone** (`YYYY-MM-DD HH:MM:SS`). Good for application-local timestamps.
- `TIMESTAMP` — Stores date and time **as UTC and converts to timezone on display** (behaviour depends on server/session timezone). Small storage and has automatic behaviours (`CURRENT_TIMESTAMP`). Good for created/updated audit fields in many web apps.

**When to use which**
- Use `DATE` for purely date fields (birthdate, holiday).
- Use `DATETIME` when timezone conversion is not desired and you need full range (1000-01-01 to 9999-12-31).
- Use `TIMESTAMP` for audit fields where server timezone conversions and `CURRENT_TIMESTAMP` automatic updates are useful. Note `TIMESTAMP` range is 1970–2038 on some systems (older), but MySQL 8 extends behavior.

### Boolean / tiny values
- `TINYINT(1)` — Commonly used as boolean (`0` or `1`). MySQL has `BOOLEAN`/`BOOL` as alias to `TINYINT(1)`.
- Use `ENUM('0','1')` rarely — prefer `TINYINT` for portability.

### Numeric types
- `INT` — 4 bytes, typical numeric id/counters.
- `BIGINT` — 8 bytes, use for large counters or IDs (e.g., global ids).
- `SMALLINT`, `MEDIUMINT` — use to save space if range small.
- `DECIMAL(p,s)` — precise decimal (money), prefer over `FLOAT/DOUBLE` for currency.

### String types
- `VARCHAR(n)` — variable-length, up to 65,535 bytes (row limit). Use for names, emails; set reasonable `n` (e.g., `VARCHAR(255)`).
- `CHAR(n)` — fixed-length, use for codes with fixed width.
- `TEXT`, `MEDIUMTEXT`, `LONGTEXT` — large text storage.

### Binary / BLOB types (for images/files)
- `BLOB` — binary large object (up to 65,535 bytes)
- `MEDIUMBLOB` — up to 16 MB
- `LONGBLOB` — up to 4 GB

**Industry note**: Prefer storing files in object storage (S3, Azure Blob) and keep URLs in DB. Use BLOB for small binary data if necessary.

### JSON
- `JSON` — MySQL native JSON type (stores as binary internal representation). Good for semi-structured data, but index key paths as needed.

---

## 2. Create Table — basic example (recommended defaults)

```sql
CREATE TABLE users (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    profile_image LONGBLOB NULL, -- image (optional)
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

Notes:
- `AUTO_INCREMENT` must be on an integer key and is typically primary key.
- `created_at` commonly uses `TIMESTAMP` with `DEFAULT CURRENT_TIMESTAMP`.
- `updated_at` uses `ON UPDATE CURRENT_TIMESTAMP` for last modified.

---

## 3. Foreign Key example & composite primary key (junction table)

```sql
CREATE TABLE products (
    product_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
    order_id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Junction table with composite primary key (primary key across two columns)
CREATE TABLE order_items (
    order_id BIGINT UNSIGNED NOT NULL,
    product_id INT UNSIGNED NOT NULL,
    qty INT NOT NULL DEFAULT 1,
    price DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

Composite primary key means uniqueness enforced across the pair. Good for many-to-many junction tables.

---

## 4. ALTER TABLE — common operations

### Rename table
```sql
RENAME TABLE old_table_name TO new_table_name;
-- or
ALTER TABLE old_table_name RENAME TO new_table_name;
```

### Add column
```sql
ALTER TABLE users
ADD COLUMN middle_name VARCHAR(100) NULL AFTER username;
```

### Drop column
```sql
ALTER TABLE users
DROP COLUMN middle_name;
```

### Show table details
```sql
DESCRIBE users;
-- or
SHOW CREATE TABLE users;
-- or view via information_schema
SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='your_db' AND TABLE_NAME='users';
```

### Rename column (MySQL 8+ uses CHANGE or RENAME COLUMN)
```sql
-- Keep same definition but different name
ALTER TABLE users
RENAME COLUMN username TO user_name;

-- or using CHANGE (older syntax): CHANGE old_name new_name datatype [other options]
ALTER TABLE users
CHANGE COLUMN user_name username VARCHAR(100) NOT NULL;
```

### Modify column type / default / nullability
```sql
-- Change datatype
ALTER TABLE users
MODIFY COLUMN username VARCHAR(150) NOT NULL;

-- Change default
ALTER TABLE users
MODIFY COLUMN is_active TINYINT(1) NOT NULL DEFAULT 1;

-- Make column NOT NULL (if no nulls present)
ALTER TABLE users
MODIFY COLUMN middle_name VARCHAR(100) NOT NULL;
```

### Remove default
```sql
ALTER TABLE users
ALTER COLUMN updated_at DROP DEFAULT;
-- or
ALTER TABLE users
MODIFY COLUMN updated_at TIMESTAMP NULL;
```

---

## 5. Constraints — add / remove / show

### Add foreign key
```sql
ALTER TABLE orders
ADD CONSTRAINT fk_orders_user
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE;
```

### Drop foreign key
You need the foreign key name (see SHOW CREATE TABLE):
```sql
ALTER TABLE orders DROP FOREIGN KEY fk_orders_user;
-- Optionally drop index if exists:
ALTER TABLE orders DROP INDEX fk_orders_user;
```

### Show constraints / foreign keys / indexes
```sql
SHOW CREATE TABLE orders;
-- Or query information_schema
SELECT
  CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA='your_db' AND TABLE_NAME='orders' AND REFERENCED_TABLE_NAME IS NOT NULL;
```

### Create / change primary key
- Add primary key (only if table has no PK)
```sql
ALTER TABLE some_table ADD PRIMARY KEY (id);
```
- Drop primary key:
```sql
ALTER TABLE some_table DROP PRIMARY KEY;
```
- Change primary key: typically drop then add new
```sql
ALTER TABLE some_table DROP PRIMARY KEY, ADD PRIMARY KEY (new_col);
```

---

## 6. Adding NOT NULL column to an existing table with data — what happens?

If you run:
```sql
ALTER TABLE t ADD COLUMN x INT NOT NULL;
```
This will fail if existing rows have no value for `x` (because NOT NULL and no default). Solutions:
1. Provide a DEFAULT during add:
```sql
ALTER TABLE t ADD COLUMN x INT NOT NULL DEFAULT 0;
```
2. Add it nullable, populate values, then alter to NOT NULL:
```sql
ALTER TABLE t ADD COLUMN x INT NULL;
UPDATE t SET x = 0 WHERE x IS NULL;
ALTER TABLE t MODIFY COLUMN x INT NOT NULL;
```
3. Use `ADD COLUMN x INT NOT NULL DEFAULT <val>` to set value for existing rows automatically.

---

## 7. Indexing & Performance (practical tips)
- Use indexes for columns used in `WHERE`, `JOIN`, `ORDER BY`.
- Avoid indexing low-selectivity columns (tinyint flags usually not indexed unless selective).
- Use composite indexes that match query patterns.
- `EXPLAIN` your queries.

---

## 8. Image & Blob considerations
- `BLOB` types store binary content inside DB.
- Prefer storing large media in object storage (S3) and keep URL + metadata in DB.
- If you must use DB:
  - Use `MEDIUMBLOB` or `LONGBLOB`.
  - Consider chunking for very large files.
  - Beware of backup size and I/O.

Example with image URL:
```sql
CREATE TABLE assets (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  url VARCHAR(2000), -- points to S3 / CDN
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 9. Advanced / Logical Questions (with answers)

### Q: What happens if I add a NOT NULL column without default to a table with millions of rows?
A: The ALTER will fail (or be blocked) because existing rows would violate NOT NULL. You must use a default or perform in steps. Also ALTER on huge tables can lock table; prefer online schema change tools (Percona pt-online-schema-change) or `ALTER TABLE ... ALGORITHM=INPLACE` if supported.

### Q: How to create a primary key using two tables?
A: You cannot create a single primary key across two separate tables. Primary key is per-table. But you can create a composite primary key made of columns that are foreign keys referencing other tables (junction table):
```sql
CREATE TABLE enrollment (
  student_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  PRIMARY KEY (student_id, course_id),
  FOREIGN KEY (student_id) REFERENCES students(id),
  FOREIGN KEY (course_id) REFERENCES courses(id)
);
```

### Q: How to change a column name and keep data?
A: Use `ALTER TABLE ... RENAME COLUMN` (MySQL 8+), or `CHANGE COLUMN oldname newname TYPE`:
```sql
ALTER TABLE users RENAME COLUMN username TO user_name;
-- or
ALTER TABLE users CHANGE COLUMN username user_name VARCHAR(150) NOT NULL;
```

### Q: How to safely remove a foreign key constraint?
A:
1. `SHOW CREATE TABLE child_table;` to get constraint name
2. `ALTER TABLE child_table DROP FOREIGN KEY fk_name;`
3. Optionally `ALTER TABLE child_table DROP INDEX fk_name;`

### Q: How to show all constraints?
A: Use `information_schema.TABLE_CONSTRAINTS` and `KEY_COLUMN_USAGE`.

Example:
```sql
SELECT *
FROM information_schema.TABLE_CONSTRAINTS
WHERE TABLE_SCHEMA='your_db' AND TABLE_NAME='your_table';
```

---

## 10. Useful SQL snippets (copy-paste)

```sql
-- Show all tables
SHOW TABLES;

-- Describe table
DESCRIBE users;

-- Show create
SHOW CREATE TABLE users\G

-- Show columns with details
SELECT COLUMN_NAME, COLUMN_TYPE, IS_NULLABLE, COLUMN_DEFAULT
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA='your_db' AND TABLE_NAME='users';

-- Add FK
ALTER TABLE child ADD CONSTRAINT fk_child_parent FOREIGN KEY (parent_id) REFERENCES parent(id);

-- Drop FK
ALTER TABLE child DROP FOREIGN KEY fk_child_parent;

-- Change datatype (keep name)
ALTER TABLE users MODIFY COLUMN salary DECIMAL(10,2) NOT NULL DEFAULT 0.00;

-- Rename column
ALTER TABLE users RENAME COLUMN old_col TO new_col;

-- Drop primary key and add composite PK
ALTER TABLE t DROP PRIMARY KEY, ADD PRIMARY KEY (col1, col2);

-- Create index
CREATE INDEX idx_user_email ON users(email);

-- Drop index
DROP INDEX idx_user_email ON users;
```

---

## 11. Best Practices (Industry)
- Use **InnoDB** for transactions and foreign keys.
- Use `utf8mb4` charset for full unicode.
- Use `BIGINT` for global ids, `INT` for local ids.
- Use `DECIMAL` for money.
- Avoid storing large files in DB; use object storage.
- Use migrations (Flyway / Liquibase / EF migrations) for schema changes.
- Backup before schema change.
- For large tables, use online schema change tools.
- Always test schema changes in staging.

---

## 12. Extra tips
- `ALTER TABLE` can be expensive — plan maintenance windows.
- Use transactions when altering multiple related schema objects.
- Consider soft-delete (`is_deleted TINYINT`) instead of physical delete for audit.
- Partition large tables for performance if needed.

---

## 13. Appendix: Useful information_schema queries
```sql
-- Find all foreign keys referencing a table
SELECT TABLE_NAME, CONSTRAINT_NAME, COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE REFERENCED_TABLE_SCHEMA = 'your_db' AND REFERENCED_TABLE_NAME = 'users';

-- List indexes
SHOW INDEX FROM users;
```

---

## 14. Helpful Resources
- MySQL 8 Documentation: https://dev.mysql.com/doc/
- InnoDB docs
- Percona Toolkit (for online alter)

---

Happy coding — if you want, I can:
- Convert this to a printable PDF
- Add screenshots / examples for a specific MySQL version
- Create migration scripts for the examples above
