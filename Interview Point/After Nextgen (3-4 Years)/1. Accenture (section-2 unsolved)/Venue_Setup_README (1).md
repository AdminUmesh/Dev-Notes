# Venue Setup Planning Agent

Educational reference implementation of an LLM-assisted conference setup planner.

## Project structure

```text
venue_setup_planning_agent/
├── app.py
├── README.md
├── ASSIGNMENT.md
├── EXPECTED_OUTPUT.md
├── QUESTION.txt
├── expected_output.json
├── requirements.txt
├── .env.example
├── src/
│   ├── config.py
│   ├── llm.py
│   ├── models.py
│   ├── scheduler.py
│   ├── toolset.py
│   └── venue_agent.py
├── prompts/
│   ├── extract.txt
│   └── repair.txt
├── examples/
│   ├── event_brief.txt
│   └── tasks.json
└── tests/
    └── test_venue_agent.py
```

## Requirements

Python 3.10+. OpenAI API access is optional.

```bash
python -m venv .venv
```

Windows PowerShell:
```powershell
.venv\Scripts\Activate.ps1
pip install -r requirements.txt
python app.py
```

macOS/Linux:
```bash
source .venv/bin/activate
pip install -r requirements.txt
python app.py
```

## Run tests

```bash
python -m unittest discover -s tests -v
```

## Optional OpenAI configuration

Set `OPENAI_API_KEY` and optionally `OPENAI_MODEL` (default `gpt-4o-mini`).
The project uses the OpenAI Python SDK when a key is present. Without a key,
the offline fallback returns empty constraints with confidence 0.50.

PowerShell:
```powershell
$env:OPENAI_API_KEY="your-api-key"
$env:OPENAI_MODEL="gpt-4o-mini"
python app.py
```

macOS/Linux:
```bash
export OPENAI_API_KEY="your-api-key"
export OPENAI_MODEL="gpt-4o-mini"
python app.py
```

## Important behavior

- Slots are one-based. Duration 2 at slot 1 finishes at slot 2.
- `ready_by` is the latest inclusive finish slot.
- `not_during` prevents any overlapping slot between the pair.
- Dependencies must finish before a dependent task starts.
- The scheduler uses exact backtracking and may become slow for large task sets.
- Toolset defaults to success; use configured outcome queues to test failures.
- Backup execution is a basic fallback, not full schedule re-optimization.
- This is a reconstructed reference solution, not the hidden company's guaranteed exact implementation or test contract.
