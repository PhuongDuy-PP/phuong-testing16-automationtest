import { Locator } from "@playwright/test";

export const highlight = async (locator: Locator): Promise<void> => {
    // truy cập trực tiếp giao diện
    await locator.evaluate((element) => {
        element.style.border = "2px solid red"
        element.style.backgroundColor = "yellow"
    })
}

export const unhighlight = async (locator: Locator): Promise<void> => {
    await locator.evaluate((element) => {
        element.style.border = ""
        element.style.backgroundColor = ""
    })
}