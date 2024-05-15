# Static
Trong java, từ khóa static được sử để:
+ quản lý bộ nhớ tốt hơn
+ sử dụng để truy cập trực tiếp thông qua 1 lớp mà không cần khởi tạo

Từ khóa static thuộc về class chứ không thuộc về instance ( thế hiện) của class

Từ khóa static áp dụng cho:
+ Variable
+ Method
+ Block
+ Nested class

Chỉ những thằng static mới chơi với nhau

=> Neus 1 biến hay 1 phương thức là static thì biến đó hoặc phương thức đó sẽ được khởi tạo ngay khi chương trình chạy và tồn tại cho đến khi chương trình kết thúc
Garbage collector

## Biến static
+ Việc cấp phát bộ nhớ cho biến static chỉ xảy ra 1 lần khi mà class được nạp vào bộ nhớ
+ Biến static có thể được sử dụng làm các thuộc tính chng, để dùng chung dữ liệu cho tất cả các object ( instances) của lớp đó và điều đó giúp cho chương trình tiết kiệm bộ nhớ hơn
+ Nếu 1 biến được khai báo với từ khóa static thì chúng ta có thể truy cập trực tiếp thông qua lớp


```java
public class Test {
    
    static int a = 5;

    public static void main(String[] args) {
        System.out.println(a);
    }
}

```

