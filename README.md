# Notes
Spigot plugin that allows you to create notes for users, based on UUID.

# Functions
-Stores notes for a player into Notes/config.yml based on user's UUID
-Hides notes marked as deleted/old when performed, never deletes them for recoverability
-Auto-hides 3m old notes
-Allows you to click and delete a note in the /notes list
-And more!

# Permissions
notes.note - Create a note for a user and see when somebody adds/deletes a note
notes.notes - View a user's notes
notes.delnote - Delete a user's note
notes.viewdeleted - View notes in /notes that are marked old or deleted

# Commands
/note <user> <note message> - Create a note for a user
/notes <user> - View a user's notes
/delnote <user> <datestamp> - Delete a user's note
