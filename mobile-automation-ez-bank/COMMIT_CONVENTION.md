# Commit Message Conventions

Use the following short list of commit types when writing messages for GitHub/GitLab. Follow the format:

<type>: <JIRA-ID> <short description>

- feat: → New feature
- fix: → Bug fix
- test: → Test cases or automation
- chore: → Small update, configs, cleanup
- docs: → Documentation
- refactor: → Code restructure
- style: → UI/UX, design changes
- perf: → Performance improvements

Examples

- feat: NILE-691 add eKYC ID Scan test cases
- test: NILE-720 automate login scenarios
- fix: NILE-802 correct API response validation
- update: NILE-650 modify onboarding steps

Notes

- Keep the short description concise (imperative mood) and reference the ticket/issue ID when available.
- Use `chore:` for dependency updates, build changes, or other housekeeping that doesn't affect product behavior.
- For larger changes, include a longer body after the short summary separated by a blank line to explain rationale and any migration steps.


