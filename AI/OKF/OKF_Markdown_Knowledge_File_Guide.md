# OKF + Markdown Knowledge Files — Practical Guide

> This guide explains how to organize Markdown (`.md`) knowledge files for an AI assistant, how Markdown syntax works, how files can reference related documents, and how to maintain a clean knowledge base.

---

# 1. What is OKF?

OKF can be thought of as a structured way of organizing knowledge so an AI system can use the information consistently.

A knowledge base may contain files such as:

```text
knowledge/
├── profile.md
├── company.md
├── products.md
├── customers.md
├── sales.md
├── policies/
│   ├── refund-policy.md
│   └── leave-policy.md
└── faq/
    ├── sales-faq.md
    └── customer-faq.md
```

Important distinction:

- Markdown (`.md`) is the file format used to write the knowledge.
- OKF is the knowledge-organization approach/framework being used by the application.
- Markdown itself does not automatically make a system RAG or an AI assistant.
- Your application decides how these files are loaded, selected, indexed, or passed to an LLM.

---

# 2. Why Markdown?

Markdown is useful for AI knowledge because it is:

- Human-readable
- Easy to edit
- Easy to version-control with Git
- Easy to split into sections/chunks
- Easy for an LLM to understand
- Lightweight
- Supported by many tools

Example:

```markdown
# Company

## Overview

Our company provides CRM software.

## Products

- CRM
- DMS
- Analytics

## Contact

Email: support@example.com
```

---

# 3. Basic Markdown Syntax

## `#` — Main Heading

Use one `#` for the main topic of a file.

```markdown
# Customer Management
```

Think:

```text
# = Main topic
```

Recommended:

```markdown
# Customer Management
```

Avoid:

```markdown
# Customer Management
# Sales
# Orders
# Contacts
```

when all of these are unrelated major sections. If they are all part of one document, use `##` for subtopics.

---

# 4. `##` — Section

Use `##` for major sections under the main topic.

```markdown
# Customer Management

## Customer Creation

## Customer Update

## Customer Deletion
```

Hierarchy:

```text
# Customer Management
    ├── ## Customer Creation
    ├── ## Customer Update
    └── ## Customer Deletion
```

---

# 5. `###` — Subsection

Use `###` when a section needs more detail.

```markdown
# Customer Management

## Customer Creation

### Required Fields

### Validation Rules

### Example
```

Hierarchy:

```text
# Main topic
  ## Section
     ### Subsection
```

Use headings to make the knowledge structure clear.

---

# 6. `**text**` — Important/Bold Text

Use `**` to emphasize important words or values.

```markdown
The customer ID is **required**.
```

Another example:

```markdown
**Important:** Customer email must be unique.
```

Do not make entire paragraphs bold.

Bad:

```markdown
**The customer must provide a valid email address and phone number before registration.**
```

Better:

```markdown
The customer must provide a **valid email address** and phone number.
```

---

# 7. `-` — Bullet List

Use `-` for lists.

```markdown
## Required Fields

- Customer Name
- Email
- Phone
- Address
```

For multiple items:

```markdown
## Supported Modules

- Sales
- Customers
- Orders
- Reports
```

Bullets are especially useful for:

- Features
- Rules
- Requirements
- Options
- Lists of values

---

# 8. Numbered Lists

Use numbers when order matters.

```markdown
## Customer Creation Process

1. Validate the request.
2. Check duplicate email.
3. Save the customer.
4. Return the customer ID.
```

Use bullets when order does NOT matter.

Use numbers when the sequence matters.

---

# 9. Code Blocks

Use triple backticks for code, commands, SQL, JSON, etc.

Example:

```markdown
```sql
SELECT * FROM Customers;
```
```

The result is:

```sql
SELECT * FROM Customers;
```

For JSON:

```markdown
```json
{
  "name": "Rahul",
  "city": "Delhi"
}
```
```

Do not put normal business knowledge inside code blocks unless formatting it as code is useful.

---

# 10. Tables

Markdown tables are useful for structured information.

Example:

```markdown
| Field | Required | Description |
|---|---|---|
| CustomerName | Yes | Customer's full name |
| Email | Yes | Unique email |
| Phone | No | Contact number |
```

Good use cases:

- Field definitions
- Configuration
- Comparisons
- Business rules
- API information

---

# 11. Links to Another Markdown File

Markdown supports links to other documents.

Example:

```markdown
See the [Sales Knowledge](sales.md).
```

If the file is inside a folder:

```markdown
See the [Refund Policy](policies/refund-policy.md).
```

This creates a reference from one knowledge file to another.

Example:

```text
knowledge/
├── sales.md
└── policies/
    └── refund-policy.md
```

In `sales.md`:

```markdown
# Sales

For refund rules, see the [Refund Policy](policies/refund-policy.md).
```

---

# 12. When Should You Reference Another Document?

Use another file when the information is:

- Related but independently useful
- Large enough to deserve its own document
- Reused by multiple topics
- Maintained separately
- A policy or reference document
- A different business domain

Example:

```text
sales.md
products.md
refund-policy.md
```

Sales can reference the refund policy without copying the entire policy.

---

# 13. Don't Duplicate Large Knowledge

Bad:

```text
sales.md
    ↓
contains the complete refund policy

customer-support.md
    ↓
contains the same complete refund policy

faq.md
    ↓
contains the same complete refund policy
```

Now the same information exists in three places.

If the refund policy changes, you must update three files.

Better:

```text
refund-policy.md
       ↑
       │
sales.md
customer-support.md
faq.md
```

Keep the actual policy in one authoritative file.

---

# 14. Important: A Markdown Link Does Not Automatically Load the Other File

This is very important for AI systems.

If `sales.md` contains:

```markdown
See [Refund Policy](refund-policy.md).
```

the Markdown link itself does NOT guarantee that your AI application will automatically read `refund-policy.md`.

Your application must implement the logic to:

1. Detect the reference.
2. Resolve the file path.
3. Load the referenced file if needed.
4. Include it in the knowledge context.

So:

```text
Markdown link
    ≠
automatic AI retrieval
```

It is a reference unless your application gives it retrieval behavior.

---

# 15. Recommended Knowledge File Structure

For an AI assistant, prefer a clear hierarchy.

Example:

```text
knowledge/
│
├── README.md
│
├── company/
│   ├── overview.md
│   ├── departments.md
│   └── contact.md
│
├── customers/
│   ├── customer-overview.md
│   ├── customer-rules.md
│   └── customer-faq.md
│
├── sales/
│   ├── sales-overview.md
│   ├── sales-rules.md
│   ├── pricing.md
│   └── sales-faq.md
│
├── policies/
│   ├── refund-policy.md
│   ├── privacy-policy.md
│   └── cancellation-policy.md
│
└── technical/
    ├── architecture.md
    ├── api.md
    └── database.md
```

---

# 16. One File = One Clear Topic

Good:

```text
refund-policy.md
```

contains refund policy.

Good:

```text
customer-rules.md
```

contains customer rules.

Avoid a huge file such as:

```text
everything.md
```

containing:

- Customer rules
- Sales
- HR
- Products
- Refund policy
- API documentation
- Database documentation
- Company history

Smaller, logically organized documents are easier to maintain and retrieve.

---

# 17. Recommended File Template

A useful pattern is:

```markdown
# Topic Name

## Purpose

Short explanation of what this document contains.

## Overview

Main knowledge.

## Rules

- Rule 1
- Rule 2
- Rule 3

## Examples

Example information.

## Related Knowledge

- [Related Topic](../path/related-topic.md)

## Important Notes

**Important:** Special instruction or exception.
```

---

# 18. Keep Facts Explicit

Bad:

```markdown
Customers normally get a refund.
```

This is ambiguous.

Better:

```markdown
## Refund Policy

Customers can request a refund within **30 days** of purchase.

Refund requests after 30 days are normally rejected unless an approved exception exists.
```

Clear facts are easier for both retrieval and the LLM.

---

# 19. Separate Facts from Examples

Bad:

```markdown
# Sales

Rahul bought 10 products, so customers usually buy many products.
```

This mixes an individual example with a business rule.

Better:

```markdown
# Sales

## Business Rule

A customer can purchase multiple products.

## Example

Rahul purchased 10 products in one order.
```

This helps the AI distinguish:

```text
Rule
vs
Example
```

---

# 20. Don't Put Frequently Changing Live Data in Static Knowledge

Avoid storing data such as:

```markdown
Today's total sales = ₹25,000
```

if the value changes every day.

Instead:

```text
Question
   ↓
AI decides live data is required
   ↓
SQL Server
   ↓
Current result
   ↓
LLM
```

Static knowledge is better for:

- Policies
- Definitions
- Procedures
- Product descriptions
- Business rules
- FAQs
- Documentation

Live SQL is better for:

- Today's sales
- Current inventory
- Customer counts
- Current orders
- Date-range reports
- Current transactions

---

# 21. Do and Don't

## DO

- Use clear filenames.
- Keep one main topic per file.
- Use `#` for the main title.
- Use `##` and `###` for hierarchy.
- Use bullet lists for rules/options.
- Use numbered lists for processes.
- Use tables for structured data.
- Use code blocks for code/SQL/JSON.
- Use explicit facts.
- Add related-document references.
- Keep one authoritative source for each important rule.
- Separate static knowledge from live database data.
- Keep documents reasonably sized.
- Use consistent terminology.
- Use version control such as Git for important knowledge bases.

## DON'T

- Don't create one giant `everything.md`.
- Don't duplicate the same policy across many files.
- Don't put constantly changing database values in Markdown.
- Don't make entire paragraphs bold.
- Don't use headings randomly.
- Don't create very small files for every sentence.
- Don't mix unrelated business domains in one file.
- Don't assume a Markdown link automatically loads another file.
- Don't write ambiguous rules.
- Don't mix examples with actual business rules without labels.
- Don't put secrets, passwords, API keys, or tokens in knowledge files.
- Don't treat an example as an actual business rule.
- Don't blindly index every file if some files contain irrelevant/internal information.

---

# 22. Knowledge Naming Convention

Use predictable filenames.

Good:

```text
customer-overview.md
customer-rules.md
sales-overview.md
sales-rules.md
refund-policy.md
product-pricing.md
```

Avoid:

```text
new.md
test.md
final.md
final2.md
important-final.md
abc.md
```

A filename should tell a developer what the file contains.

---

# 23. Metadata Can Help Your Application

You can add metadata at the top of a file if your application supports it.

Example:

```markdown
# Sales Rules

**Domain:** Sales
**Type:** Business Rule
**Version:** 1.2
**Status:** Active
**Last Updated:** 2026-10-08

## Rules

- Sales orders require a customer.
- Cancelled orders are excluded from completed-sales reports.
```

Your application can potentially use these fields when indexing or filtering knowledge.

---

# 24. OKF + RAG Can Work Together

A useful architecture is:

```text
                 AI Assistant
                      ↓
              Knowledge Router
               ↙            ↘
       Static Knowledge      RAG
          / OKF              ↓
            ↓             Qdrant
            │                ↓
            └──────┬─────────┘
                   ↓
                  LLM
                   ↓
                Answer
```

For example:

Question:

> What is the refund policy?

→ Retrieve controlled policy knowledge.

Question:

> Which document explains the refund process?

→ RAG can search document knowledge.

Question:

> What were today's sales?

→ SQL Server, not static Markdown.

---

# 25. Best Practice for Your CRM AI Assistant

For your .NET + Angular + SQL Server project, consider:

```text
knowledge/
│
├── company/
├── products/
├── sales/
├── policies/
└── faq/
```

Then:

```text
Static business knowledge
        ↓
Markdown / OKF
        ↓
Knowledge service

Large/unstructured documents
        ↓
Chunking
        ↓
Embeddings
        ↓
Qdrant
        ↓
RAG service

Live CRM data
        ↓
SQL Server
        ↓
Analytics service

All relevant context
        ↓
LLM
        ↓
Final answer
```

---

# 26. Simple Rule to Remember

Use:

```text
#       → Main topic
##      → Major section
###     → Subsection
**...** → Important/emphasis
-       → Bullet/list item
1.      → Ordered steps
[...](...) → Reference/link
```...``` → Code / JSON / SQL
|...|   → Table
```

And for knowledge architecture:

```text
One topic
   ↓
One logical document
   ↓
Clear sections
   ↓
References to related documents
   ↓
No unnecessary duplication
```

## Final Principle

The goal is not to create the most Markdown.

The goal is to create **clear, authoritative, maintainable knowledge that your application can reliably retrieve and provide to the LLM.**
