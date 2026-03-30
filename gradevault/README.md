# GradeVault — Student Grade Tracker

A fully functional multi-page website for tracking student grades, powered by **IndexedDB** for persistent local database storage.

## Project Structure

```
gradevault/
├── index.html              # Dashboard (home)
├── css/
│   └── style.css           # Global stylesheet
├── js/
│   ├── db.js               # IndexedDB database layer
│   └── app.js              # Shared utilities (gradeFromScore, exportCSV)
└── pages/
    ├── grades.html         # All Grades — CRUD table with search/filter
    ├── analytics.html      # Analytics — charts and insights
    └── students.html       # Students — per-student summary view
```

## Features

- **CRUD Operations** — Add, edit, and delete grade records from the Grades page
- **IndexedDB Database** — All data persists locally across page reloads, no backend needed
- **Search & Filter** — Search by student name or subject; filter by grade letter or subject; sort by multiple fields
- **Analytics** — Grade distribution doughnut chart, average by subject bar chart, score breakdown, top students leaderboard
- **Students View** — Per-student average, grade, subjects, and progress bar
- **Export CSV** — Download all records as a `.csv` file (from the Grades page)
- **Sample Data** — Automatically seeds 8 sample records on first launch

## How to Run

### Option 1: VS Code Live Server (recommended)
1. Open the `gradevault` folder in VS Code
2. Install the **Live Server** extension (by Ritwick Dey)
3. Right-click `index.html` → **Open with Live Server**

### Option 2: Python local server
```bash
cd gradevault
python3 -m http.server 8080
# Then open http://localhost:8080 in your browser
```

### Option 3: Node.js
```bash
cd gradevault
npx serve .
```

> **Note:** Do NOT open `index.html` directly as a `file://` URL — IndexedDB requires a proper HTTP server to work correctly in all browsers.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| UI | HTML5 + CSS3 (custom, no framework) |
| Database | IndexedDB (browser-native) |
| Charts | Chart.js 4.4 |
| Fonts | DM Sans + DM Mono (Google Fonts) |

## Database Schema

**Object Store:** `grades`

| Field | Type | Description |
|-------|------|-------------|
| `id` | Auto-increment | Primary key |
| `name` | String | Student full name |
| `subject` | String | Subject name |
| `score` | Number | Score (0–100) |
| `date` | String | ISO date string (YYYY-MM-DD) |
| `notes` | String | Optional notes |

**Indexes:** `name`, `subject`, `date`
