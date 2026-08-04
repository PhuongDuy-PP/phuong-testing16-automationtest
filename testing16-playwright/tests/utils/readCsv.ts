// convert từ csv sang json
// "Admin,admin123,success" => {
//     username: "Admin",
//     password: "admin123",
//     expected: "success"
// }
// gạch đỏ là do .ts không có hiểu thư viện này ở đâu
// vì fs có sẵn trong typescript, nên không cần cài đặt thêm thư viện nào cả
import fs from "fs"

// Record: tạo ra object với key là string, value là string
export type CsvRow = Record<string, string>

// tham số: relative path của file csv. VD: /data/loginData.csv
export const readCsv = (filePath: string): CsvRow[] => {
    // convert relative path sang absolute path để playwright có thể tìm được file
    // process.cwd() => trả về đường dẫn tuyệt đối của thư mục gốc của dự án
    const absolutePath = `${process.cwd()}/tests${filePath}`
    console.log(`absolutePath = ${absolutePath}`)

    // đọc toàn bộ nội dung của file csv
    // nội dung của file csv chỉ là 1 string, các dòng được phân tách nhau
    // bằng dấu \n (new line)
    const csvContent = fs.readFileSync(absolutePath, "utf-8")

    // tách các dòng trong file csv thành 1 mảng
    const lines = csvContent.split("\n")

    // bỏ qua header row (dòng đầu tiên)
    const dataLines = lines.slice(1)

    const rows: CsvRow[] = []
    // flow xử lý từng dòng trong file csv
    // "Admin,admin123,success" => ["Admin", "admin123", "success"]
    // ["Admin", "admin123", "success"] => {
    //     username: "Admin",
    //     password: "admin123",
    //     expected: "success"
    // }
    for (const line of dataLines) {
        const columns = line.split(",")

        // nâng cao: sau này nếu có nhiều loại dữ liệu hơn
        // tạo 1 hàm sử lý được nhiều loại dữ liệu
        switch (filePath) {
            case "/data/loginData.csv":
                const row: CsvRow = {
                    username: columns[0],
                    password: columns[1],
                    expected: columns[2]
                }
                rows.push(row)
                break
            default:
                throw new Error(`Unsupported CSV file: ${filePath}`)
        }
    }
    return rows
}
