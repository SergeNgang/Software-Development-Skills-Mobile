# Software-Development-Skills-Mobile
# TaskCraft — Android Weekly Planner

**TaskCraft** is a native Android weekly planner application built with Kotlin. It provides a clean, single-row 7-day filter interface to organize daily activities, track completion states in real time, inspect task details, and schedule new events.

🔗 **Demo Video:** ): https://drive.google.com/file/d/1idk04paj9rYRRxTxflbZ8KoVqj86rKP6/view?usp=sharing

---

## Key Features

* **7-Day Filter Bar:** Equal-weight single-row header buttons (**All**, **Mon**, **Tue**, **Wed**, **Thu**, **Fri**, **Sat**, **Sun**) for instant task filtering across the week.
* **Dynamic Task List (`ListView`):** Custom `BaseAdapter` (`ActivityAdapter`) rendering task titles, scheduled time windows, and day badges.
* **Real-Time Completion Togglers:** Interactive `CheckBox` controls on every row that dynamically apply a strikethrough effect to completed tasks.
* **Task Creation Flow (`AddActivity`):** Floating Action Button (`+`) navigation to an input form for adding new activities with title, target day, start time, and end time.
* **Detailed Activity View (`DetailActivity`):** Tapping any task opens a dedicated screen displaying full metadata alongside implicit intents for opening external reference links and sharing tasks.

---

## Architecture & Tech Stack

* **Language:** Kotlin
* **UI Framework:** Android XML Layouts (`RelativeLayout`, weighted `LinearLayout`, `ListView`, `FloatingActionButton`)
* **Components & Navigation:**
  * `MainActivity` — Main weekly view, day filtering, and list management.
  * `AddActivity` — Task input form returning new items via explicit intent results.
  * `DetailActivity` — Task detail display with `ACTION_VIEW` and `ACTION_SEND` system intents.
* **Data Model:** `ActivityItem` implementing `Serializable` for passing structured objects between Activities.

---

## Project Structure

```text
app/src/main/
├── AndroidManifest.xml
├── java/com/example/taskcraft/
│   ├── MainActivity.kt        # Main weekly planner & filter logic
│   ├── AddActivity.kt         # New task creation screen
│   ├── DetailActivity.kt      # Task details & external sharing
│   ├── ActivityAdapter.kt     # Custom BaseAdapter for ListView
│   └── ActivityItem.kt        # Serializable task data model
└── res/layout/
    ├── activity_main.xml      # 7-day filter bar & ListView layout
    ├── activity_add.xml       # Task input form layout
    ├── activity_detail.xml    # Detail screen layout
    └── item_activity.xml      # Individual task row card
