# Contributing to the Time To-Do Project
"Time To-Do” is a productivity tool designed to help users achieve their goals and form positive habits.
It allows users to systematically manage their objectives and visualize their achievements, providing continuous motivation.
Features like Challenge Mode, Calendar Integration, and Achievement Graphs allow users to track their accomplished goals immediately easily.
This goes beyond simple task management to offer the joy of accomplishment and growth.
<br><br>
Thank you for considering contributing to our project! We value your time and effort.<br>
Please follow these guidelines to ensure a smooth and effective contribution process.


## How to Report Issues
- Go to the **Issues** tab in the repository.
- Click on "New Issue" and select the appropriate template. There is three template.
  - Bug Report
  - Feature Request
  - Refactoring
- Provide a clear and concise title and description for your issue.
- Attach screenshots or code snippets if applicable.
- Click on "Submit new issue" to create the issue.


## How to Submit a Pull Request
1. Fork the repository.
2. Clone your forked repository to your local machine.
3. Create a new branch for your changes:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. Make your changes and commit them:
   ```bash
    git commit -m "Feat: Add a meaningful commit message"
   ```
5. Push your changes to your forked repository:
   ```bash
    git push origin feature/your-feature-name
   ```
6. Open a Pull Request in the original repository.
7. Provide a clear and concise title and description for your Pull Request.

## Commit Message and PR Title Guidelines
- Use the format: `<Type>: <Short Description>`
    - **Type**: Feat, Fix, Refactor, Docs, etc.
    - Example: `Feat: Add user authentication feature`
- Keep the title concise and to the point.
- Do not use punctuation (e.g., `!`, `.`, `?`) at the end of the title.
- You can check the type at [.gitmessage](.gitmessage) file.

## Testing
- Before submitting a Pull Request, make sure to test your changes.
- - Unit tests are written using **JUnit 5** and are located in the `src/test/java` directory.
- Run the tests to ensure that your changes do not break the existing code.
- If you have added new features, write tests for them.
- Make sure that all tests pass before submitting your Pull Request.
- Please write about test that you have done in the Pull Request description.

## License
By contributing to this project, you agree that your contributions will be licensed under the project's [MIT LICENSE](LICENSE).

## Need Help?
If you have any questions, feel free to open an issue or contact us at easy1nhard2@gmail.com.
