// kiểu dữ liệu
// 1. kiểu nguyên thủy (primitive types): string, number, boolean, null,...
let username: string = "Phuong"
let age: number = 20
let isStudent: boolean = true

// in ra giá trị của biến
console.log(username)
console.log(age)
console.log(isStudent)

let numbers: number[] = [1, 2, 3, 4, 5]
console.log(numbers)

let names: string[] = ["Phuong", "Huy", "Nam"]
console.log(names)

// any: HẠN CHẾ DÙNG

// unknown: kiểu dữ liệu chưa biết, cần kiểm tra trước khi sử dụng
// js => ts: unknown => cần kiểm tra kiểu dữ liệu trước khi sử dụng
// typeof: kiểm tra kiểu dữ liệu của biến
let randomValue: unknown = 10
if (typeof randomValue === "number") {
    console.log("randomValue là số")
} else {
    console.log("randomValue không phải là số")
}

// null: kiểu dữ liệu rỗng, không có giá trị
let emptyVal = null

// undefined: kiểu dữ liệu chưa được gán giá trị
// VD: tạo biến nhưng chưa gán giá trị => defeault value là undefined
let assignValue;
console.log(assignValue) // undefined

// 2. kiểu đối tượng: Array, Object, Date,...

