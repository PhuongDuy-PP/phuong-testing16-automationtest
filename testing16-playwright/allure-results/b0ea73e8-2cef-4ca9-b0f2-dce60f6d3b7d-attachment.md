# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: login.spec.ts >> Login tests with data from CSV >> Login with username: Admin, expected: success
- Location: tests/login.spec.ts:48:13

# Error details

```
Error: locator.evaluate: Test ended.
Call log:
  - waiting for getByRole('button', { name: 'Login' })

```

# Test source

```ts
  1  | import { Locator } from "@playwright/test";
  2  | 
  3  | export const highlight = async (locator: Locator): Promise<void> => {
  4  |     // truy cập trực tiếp giao diện
  5  |     await locator.evaluate((element) => {
  6  |         element.style.border = "2px solid red"
  7  |         element.style.backgroundColor = "yellow"
  8  |     })
  9  | }
  10 | 
  11 | export const unhighlight = async (locator: Locator): Promise<void> => {
> 12 |     await locator.evaluate((element) => {
     |                   ^ Error: locator.evaluate: Test ended.
  13 |         element.style.border = ""
  14 |         element.style.backgroundColor = ""
  15 |     })
  16 | }
```