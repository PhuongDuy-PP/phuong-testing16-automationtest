# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: myinfor.spec.ts >> My Info tests >> Verify My Info page is displayed
- Location: tests/myinfor.spec.ts:17:9

# Error details

```
TimeoutError: locator.waitFor: Timeout 30000ms exceeded.
Call log:
  - waiting for locator('//button[@class=\'employee-image-action\']') to be visible

```

# Page snapshot

```yaml
- generic [ref=f3e3]:
  - generic:
    - complementary [ref=f3e4]:
      - navigation "Sidepanel" [ref=f3e5]:
        - generic [ref=f3e6]:
          - link [ref=f3e7] [cursor=pointer]:
            - /url: https://www.orangehrm.com/
            - img "client brand banner" [ref=f3e9]
          - text: 
        - generic [ref=f3e10]:
          - generic [ref=f3e11]:
            - generic [ref=f3e12]:
              - textbox "Search" [ref=f3e15]
              - button "" [ref=f3e16] [cursor=pointer]
            - separator [ref=f3e18]
          - list [ref=f3e19]:
            - listitem [ref=f3e20]:
              - link "Admin" [ref=f3e21] [cursor=pointer]:
                - /url: /web/index.php/admin/viewAdminModule
            - listitem [ref=f3e25]:
              - link "PIM" [ref=f3e26] [cursor=pointer]:
                - /url: /web/index.php/pim/viewPimModule
            - listitem [ref=f3e41]:
              - link "Leave" [ref=f3e42] [cursor=pointer]:
                - /url: /web/index.php/leave/viewLeaveModule
            - listitem [ref=f3e46]:
              - link "Time" [ref=f3e47] [cursor=pointer]:
                - /url: /web/index.php/time/viewTimeModule
            - listitem [ref=f3e54]:
              - link "Recruitment" [ref=f3e55] [cursor=pointer]:
                - /url: /web/index.php/recruitment/viewRecruitmentModule
            - listitem [ref=f3e62]:
              - link "My Info" [ref=f3e63] [cursor=pointer]:
                - /url: /web/index.php/pim/viewMyDetails
            - listitem [ref=f3e70]:
              - link "Performance" [ref=f3e71] [cursor=pointer]:
                - /url: /web/index.php/performance/viewPerformanceModule
            - listitem [ref=f3e80]:
              - link "Dashboard" [ref=f3e81] [cursor=pointer]:
                - /url: /web/index.php/dashboard/index
            - listitem [ref=f3e85]:
              - link "Directory" [ref=f3e86] [cursor=pointer]:
                - /url: /web/index.php/directory/viewDirectory
            - listitem [ref=f3e90]:
              - link "Maintenance" [ref=f3e91] [cursor=pointer]:
                - /url: /web/index.php/maintenance/viewMaintenanceModule
            - listitem [ref=f3e96]:
              - link "Claim" [ref=f3e97] [cursor=pointer]:
                - /url: /web/index.php/claim/viewClaimModule
            - listitem [ref=f3e105]:
              - link "Buzz" [ref=f3e106] [cursor=pointer]:
                - /url: /web/index.php/buzz/viewBuzz
    - banner [ref=f3e110]:
      - generic [ref=f3e111]:
        - generic [ref=f3e112]:
          - text: 
          - heading "PIM" [level=6] [ref=f3e114]
        - link [ref=f3e116]:
          - /url: https://orangehrm.com/open-source/upgrade-to-advanced
          - button "Upgrade" [ref=f3e117] [cursor=pointer]
        - list [ref=f3e123]:
          - listitem [ref=f3e124]:
            - generic [ref=f3e125] [cursor=pointer]:
              - img "profile picture" [ref=f3e126]
              - paragraph [ref=f3e127]: manda user
              - generic [ref=f3e128]: 
      - navigation "Topbar Menu" [ref=f3e130]:
        - list [ref=f3e131]:
          - button "" [ref=f3e133] [cursor=pointer]
  - generic [ref=f3e135]:
    - generic [ref=f3e139]:
      - generic [ref=f3e140]:
        - generic [ref=f3e141]:
          - heading "manda user" [level=6] [ref=f3e143]
          - img "profile picture" [ref=f3e146] [cursor=pointer]
        - tablist [ref=f3e147]:
          - tab [ref=f3e148]:
            - link "Personal Details" [ref=f3e149] [cursor=pointer]:
              - /url: /web/index.php/pim/viewPersonalDetails/empNumber/7
          - tab [ref=f3e150]:
            - link "Contact Details" [ref=f3e151] [cursor=pointer]:
              - /url: /web/index.php/pim/contactDetails/empNumber/7
          - tab [ref=f3e152]:
            - link "Emergency Contacts" [ref=f3e153] [cursor=pointer]:
              - /url: /web/index.php/pim/viewEmergencyContacts/empNumber/7
          - tab [ref=f3e154]:
            - link "Dependents" [ref=f3e155] [cursor=pointer]:
              - /url: /web/index.php/pim/viewDependents/empNumber/7
          - tab [ref=f3e156]:
            - link "Immigration" [ref=f3e157] [cursor=pointer]:
              - /url: /web/index.php/pim/viewImmigration/empNumber/7
          - tab [ref=f3e158]:
            - link "Job" [ref=f3e159] [cursor=pointer]:
              - /url: /web/index.php/pim/viewJobDetails/empNumber/7
          - tab [ref=f3e160]:
            - link "Salary" [ref=f3e161] [cursor=pointer]:
              - /url: /web/index.php/pim/viewSalaryList/empNumber/7
          - tab [ref=f3e162]:
            - link "Report-to" [ref=f3e163] [cursor=pointer]:
              - /url: /web/index.php/pim/viewReportToDetails/empNumber/7
          - tab [ref=f3e164]:
            - link "Qualifications" [ref=f3e165] [cursor=pointer]:
              - /url: /web/index.php/pim/viewQualifications/empNumber/7
          - tab [ref=f3e166]:
            - link "Memberships" [ref=f3e167] [cursor=pointer]:
              - /url: /web/index.php/pim/viewMemberships/empNumber/7
      - generic [ref=f3e169]:
        - heading "Change Profile Picture" [level=6] [ref=f3e170]
        - separator [ref=f3e171]
        - generic [ref=f3e172]:
          - generic [ref=f3e174]:
            - generic [ref=f3e176]:
              - button "Choose File"
              - generic [ref=f3e177]:
                - img "profile picture" [ref=f3e179]
                - button "" [ref=f3e180] [cursor=pointer]
            - paragraph [ref=f3e182]: "Accepts jpg, .png, .gif up to 1MB. Recommended dimensions: 200px X 200px"
          - separator [ref=f3e183]
          - button "Save" [ref=f3e185] [cursor=pointer]
    - generic [ref=f3e186]:
      - paragraph [ref=f3e187]: OrangeHRM OS 5.9
      - paragraph [ref=f3e188]:
        - text: © 2005 - 2026
        - link "OrangeHRM, Inc" [ref=f3e189] [cursor=pointer]:
          - /url: http://www.orangehrm.com
        - text: . All rights reserved.
```

# Test source

```ts
  1  | import { Locator, Page } from "@playwright/test";
  2  | 
  3  | export class MyInforPage {
  4  |     readonly page: Page
  5  | 
  6  |     // locator
  7  |     readonly avatarWrapper: Locator
  8  |     readonly uploadBtn: Locator
  9  |     readonly fileInput: Locator
  10 | 
  11 |     constructor(page: Page) {
  12 |         this.page = page
  13 | 
  14 |         this.avatarWrapper = page.locator("//div[@class='orangehrm-edit-employee-image-wrapper']")
  15 |         this.uploadBtn = page.locator("//button[@class='employee-image-action']")
  16 |         this.fileInput = page.locator("//input[@type='file']")
  17 |     }
  18 | 
  19 |     async uploadAvatar(filePath: string): Promise<void> {
  20 |         // truy cập vào trang MyInfor
  21 |         // https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7
  22 |         await this.page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7", {
  23 |             waitUntil: "domcontentloaded",
  24 |             timeout: 30000
  25 |         })
  26 | 
  27 |         // click vào avatar wrapper để hiển thị button upload
  28 |         await this.avatarWrapper.waitFor({ state: "visible", timeout: 30000 })
  29 |         await this.avatarWrapper.click()
  30 | 
  31 |         // click vào button upload
> 32 |         await this.uploadBtn.waitFor({ state: "visible", timeout: 30000 })
     |                              ^ TimeoutError: locator.waitFor: Timeout 30000ms exceeded.
  33 |         await this.uploadBtn.click()
  34 | 
  35 |         // upload file
  36 |         // convert filePath to absolute path
  37 |         const absolutePath = `${process.cwd()}/tests${filePath}`
  38 |         console.log(`absolutePath = ${absolutePath}`)
  39 |         
  40 |         await this.fileInput.setInputFiles(absolutePath)
  41 |         await this.page.waitForTimeout(10000)
  42 |     }
  43 | 
  44 |     // hàm kiểm tra upload avatar thành công hay không
  45 |     // input[type='file'] luôn luôn trả về value dạng "<path>/fakepath/<filename>"
  46 |     // chỉ cần kiểm value có chứa tên file hay không
  47 |     async isUploadAvatarSuccessful(fileName: string): Promise<boolean> {
  48 |         const value = await this.fileInput.inputValue()
  49 |         return value.includes(fileName)
  50 |     }
  51 | }
```