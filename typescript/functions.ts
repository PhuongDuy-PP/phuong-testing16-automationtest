// function - code theo kiểu ES6 - chuẩn code mới
// ES6:
// 1. arrow function
// () => {}
// java: () -> {}: anonymous function
// syntax:
// const <function name> = (<param1>, <param2>,...) => {
//      code
// }

const sum = (number1: number, number2: number): number => {
    return number1 + number2
}

console.log(sum(10, 20))

// một số biến thể của arrow function
// TH1: trong thân hàm chỉ có 1 dòng code => bỏ dấu ngoặc {} và return
// LƯU Ý: khi giữ lại dấu {} => bắt buộc phải có return
const test1 = (number: number): number => number * 2
console.log(test1(10))

const test2 = (number: number): number => {
    return number * 2
}
// function có kiểu void => ko cần return
const test3 = (): void => {
    console.log("test3")
}

console.log(test3())

// VD1: viết hàm tính tổng của 1 mảng số
const sumArray = (numbers: number[]): number => {
    // cách 1: dùng vòng lặp for
    let total = 0;
    // for (let i = 0; i < numbers.length; i++) {
    //     total += numbers[i]
    // }

    // // for ngắn hơn - for of
    // for (let number of numbers) {
    //     total += number
    // }

    // // forEach: dùng hàm có sẵn trong typeScript
    numbers.forEach((number) => {
        total += number
    })
    return total

    // cách 2: dùng hàm reduce có sẵn trong typeScript
    // let total1 = numbers.reduce((total, number) => total + number, 0)
    // return total1
}
let numberArray: number[] = [1, 2, 3, 4, 5]
console.log(sumArray(numberArray))


// 2. default parameter
const sumThreeNumbers = (num1: number, num2: number, num3: number = 10) => {
    return num1 + num2 + num3
}
console.log(sumThreeNumbers(1, 2)) // 13
console.log(sumThreeNumbers(1, 2, 3)) // 6


// 3. rest parameter
// 4. spread operator
// 5. destructuring assignment
// 6. class
class Person {
    // thuộc tính - property
    private name: string
    private age: number
    private email: string

    // constructor: hàm khởi tạo
    constructor(name: string, age: number, email: string) {
        this.name = name
        this.age = age
        this.email = email
    }
    
    // phương thức - method
    getInfo(): void {
        // truyền variable vào string => template string
        console.log(`
            Name: ${this.name}
            Age: ${this.age}
            Email: ${this.email}    
        `)
    }
}

let person1 = new Person("Nguyen Van A", 20, "nguyenvana@email.com")
person1.getInfo()

// kế thừa - inheritance
class Student extends Person {
    private studentId: string
    private major: string

    constructor(name: string, age: number, email: string, studentId: string, major: string) {
        super(name, age, email) // gọi constructor của class cha
        this.studentId = studentId
        this.major = major
    }

    getInfo(): void {
        super.getInfo() // gọi phương thức getInfo() của class cha
        console.log(`
            Student ID: ${this.studentId}
            Major: ${this.major}
        `)
    }
}

let student1 = new Student("Nguyen Van B", 21, "nguyenvanb@gmail.com", "S001", "Computer Science")
student1.getInfo()

// ứng dụng: viết POM pattern cho project test automation
// class LoginPage, LoginTest,...

// 7. template string
// 8. module