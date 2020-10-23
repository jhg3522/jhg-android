# **Kmu-MobilePrograming**

## Personal-Project

Android Studio Java 언어 사용, Firebase Realtime Database, Storage 사용

주요기능 - 1. 상품페이지 2. 장바구니 페이지 3. 구매페이지

1. 상품 페이지

<img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023202144858.png" alt="image-20201023202144858" style="zoom: 67%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023202534396.png" alt="image-20201023202534396" style="zoom: 67%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204054332.png" alt="image-20201023204054332" style="zoom:67%;" />

`상품 페이지는 Relative Layout으로 제작.`

`각 상품은 Gridview를 활용하여 화면에 출력.` 

`상품의 이미지는 Glide를 활용하여 미리 Firebase Storage에 저장한 img-url을 받아와 출력.`

`상품 선택시 class의 boolean타입을 이용하여 true값을 주어서 선택 표시`

`CART 버튼을 누를 시 true값인 상품들만 Firebase Realtime Database의 ITEM의 하위로 저장 후 장바구니 페이지로 이동.`

`BUY 버튼을 누를 시 true값인 상품들만 Firebase Realtime Database의 BUY의 하위로 저장 후 구매 페이지로 이동.`



2. 장바구니 페이지

<img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023203111902.png" alt="image-20201023203111902" style="zoom:67%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023203202590.png" alt="image-20201023203202590" style="zoom:67%;" /><img src="C:\Users\jhg35\Desktop\캡처.JPG" alt="캡처" style="zoom:67%;" />

`장바구니 페이지는 Linear Layout으로 제작.`

`Firebsae Realtime Database에 Item 하위 노드를 받아와 ListView를 활용하여 화면에 출력.`

`상품페이지와 동일하게 boolean 타입을 통해서 선택하여 BUY 버튼을 누를시 BUY 하위노드에 저장 후 구매 페이지로 이동`

`오른쪽 상단에 휴지통 버튼을 누를시에 ITEM노드에 있는 데이터가 삭제되면서 장바구니 비우기 기능 추가.`

`Home버튼을 누를시 데이터는 남아있는 상태로 상품페이지로 이동.`



3. 구매페이지

<img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204208021.png" alt="image-20201023204208021" style="zoom:67%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204258140.png" alt="image-20201023204258140" style="zoom:66%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204332162.png" alt="image-20201023204332162" style="zoom:66%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204848021.png" alt="image-20201023204848021" style="zoom:67%;" />



구매페이지는 Linear Layout 안에 Grid Layout을 사용하여 배송정보 입력란을 제작.

firebase에 BUY노드에 있는 데이터를 ListView로 출력.

각 상품의 가격을 더하여 총합을 출력.

배송정보중에 하나라도 입력하지 않으면 "배송 정보를 전부 입력해주세요" Toast 메세지 출력.

HOME버튼을 누를시 BUY의 데이터 삭제하여 BUY 누를시 "선택된 상품이 없습니다" 출력.

하지만 ITEM의 데이터는 그대로 남아있으므로 장바구니는 그대로 존재.

배송 정보를 모두 입력 후 BUY를 누를시 HOME화면으로 돌아가고 Database 초기화.

<img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023204955470.png" alt="image-20201023204955470" style="zoom:67%;" /><img src="C:\Users\jhg35\AppData\Roaming\Typora\typora-user-images\image-20201023205019439.png" alt="image-20201023205019439" style="zoom:67%;" />





