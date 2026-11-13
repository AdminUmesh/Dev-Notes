# Reconstructed Assignment: Venue Setup Planning Agent

This is reconstructed from the screenshots and code shared in the conversation. It is not claimed to be the company's verbatim original prompt.

## Problem

Build an agent that prepares a one-day conference venue. It reads an operations lead's free-text brief, extracts event-specific rules, then schedules and executes setup crew tasks while respecting those rules, dependencies and crew limits.

Implement `run(event_id, tasks, brief)` in `src/venue_agent.py`.

## Task fields

- `id`: unique exact task identifier.
- `name`: readable task name.
- `duration_slots`: positive integer duration.
- `crew_required`: positive integer crew requirement.
- `depends_on`: task IDs that must finish first.
- `mandatory`: whether the task is required.
- `backup_of`: optional task ID this task can replace.

## LLM output contract

Return JSON with exactly:
```json
{
  "ready_by": {"<task_id>": 4},
  "not_during": [["<task_id>", "<task_id>"]],
  "elevated_mandatory": ["<task_id>"],
  "drop_priority": ["<task_id>"],
  "confidence": 0.95
}
```

- `ready_by`: latest inclusive finish slot.
- `not_during`: pairs that must not overlap.
- `elevated_mandatory`: optional tasks promoted to mandatory.
- `drop_priority`: optional tasks in order to drop first.
- `confidence`: 0 to 1.

Use only exact task IDs. Do not invent rules absent from the brief.

## Expected behavior

1. Validate tasks, IDs, durations, crew and dependencies.
2. Detect cycles.
3. Extract constraints as JSON; repair malformed JSON once.
4. Validate schema, confidence and task references.
5. Ignore extracted constraints below the confidence threshold.
6. Generate a schedule respecting dependencies, deadlines, crew capacity and non-overlap.
7. Drop optional tasks in exact priority order if no schedule exists. Never drop mandatory tasks.
8. Execute tasks; retry transient failures at most once.
9. Use declared backup tasks when a primary task fails.
10. Enforce a tool-call ceiling.
11. Return schedule, completed/failed/dropped tasks, rejected constraints and trace.

## Expected report shape

```json
{
  "event_id": "FAIRGROUND-LIVE-001",
  "status": "completed",
  "schedule": [{"task_id": "T1", "start_slot": 1, "finish_slot": 2}],
  "completed_task_ids": ["T1"],
  "failed_task_ids": [],
  "dropped_task_ids": [],
  "rejected_constraints": [],
  "trace": [{"step": "T1", "kind": "execution", "attempt": 1, "status": "SUCCESS"}],
  "error": null
}
```

## Skills being tested

Python OOP, LLM integration, prompt engineering, JSON validation, graph algorithms, cycle detection, constraint satisfaction, resource-constrained scheduling, retries, fallback execution, testing and modular software design.

## Assumptions in this reference

The screenshots did not show every model, scheduler interface, hidden test or exact report contract. This implementation defines a consistent learning contract: one-based integer slots, one crew capacity, one retry for transient failures, and a backtracking scheduler. Backup handling is a basic fallback rather than full schedule replanning.
