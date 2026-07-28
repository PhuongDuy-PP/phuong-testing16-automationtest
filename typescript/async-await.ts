// async-await - bất đồng bộ
// DEV: FE, BE - call API
// QC - playwright code bằng ts, js => async-await cho từng step
// VÌ API, tương tác với browser không phải là của playwright => nên phải đợi response, phản hồi về từ
// API hoặc browser => nên phải dùng async-await
// kiểu dữ liệu Promise => là 1 object, có 3 trạng thái: pending, fulfilled, rejected

// giả lập call API =>setTimeout
const delay = async (ms: number): Promise<void> => {
    return new Promise((resolve) => setTimeout(resolve, ms))
}

const enterUsername = async (username: string): Promise<void> => {
    await delay(5000)
    console.log(`Enter username: ${username}`)
}

// Promise.all => chạy song song nhiều promise: call API, tương tác với browser

enterUsername("admin")