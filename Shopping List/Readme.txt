Implementation Notes:
- Implemented using Android SDK r20

Current Issues:
- Minimal/Non-existent error checking
- Application does not save any values (Need to investigate SQLite database)
- Disable UI elements on the NewItemActivity when not all of the fields have input.
- No way to filter out in the UI items of a specific priority. I would like to add an option to pick a priority
(High, medium, low) or all and have only those displayed
- Use custom cells instead of text to display Name, Price and Priority
- Find a way to create headings in the List for each of the priorities (iOS does this with headers and they're all grouped
under it)
- Add ability to delete/edit items properly

Changes against specification:
- Does not require a fixed-number of items to be added.
- When "type" was specified per requirement I assumed this was the name, as the term was ambiguous. I assumed type meant items with a specific name.
- Disallowed items of the same type (Item name) by updating the values as they entered them.

Ideas for expansion:
- Connect list to Cloud via Parse SDK or Google App Engine
- Shared shopping lists
- Recurring items (Like Toilet Paper)
- Better algorithm for telling you what items to buy. Mike suggested the Napsack problem
