1. Tách nội dung thành các dòng:

```java
String[] lines = content.split("\\r?\\n");
```

Dùng biểu thức chính quy \\r?\\n để chia nội dung thành các dòng. Điều này hoạt động cho cả dấu xuống dòng Windows (\r\n) và Unix (\n).

2. Duy trì các dòng trống:

```java
if (line.trim().isEmpty()) {
    // Preserve empty lines
    normalizedContent.append("\n");
} else {
    // Normalize non-empty lines
    // (normalization code)
    normalizedContent.append(normalizedLine.toString().trim()).append("\n");
}
```
- Nếu dòng hiện tại là dòng trống (sau khi loại bỏ khoảng trắng ở hai đầu), giữ lại dòng trống trong kết quả.

3. Chuẩn hóa các dòng không trống:

Các dòng không trống được xử lý như trước đây: loại bỏ khoảng trắng dư thừa, chuẩn hóa dấu câu, viết hoa chữ cái đầu mỗi câu và đảm bảo dấu chấm ở cuối câu.

4. Đảm bảo dấu chấm ở cuối văn bản:

```java
String result = normalizedContent.toString().trim();
if (!result.endsWith(".")) {
    result += ".";
}
```
Loại bỏ khoảng trắng cuối cùng của chuỗi và kiểm tra xem văn bản có kết thúc bằng dấu chấm không. Nếu không, thêm dấu chấm.