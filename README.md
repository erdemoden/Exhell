# ExHell Library 
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Ferdemoden%2FExhell.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Ferdemoden%2FExhell?ref=badge_shield)

<p align="center">
  <img src="ExHell.jpg" width="400" height="400">
</p>

The beauty of Strategy and Builder design patterns.
Exhell is a library for dto to excel conversion with styles. It just uses apache poi library.It was inspired by the Poiji library

Don't wory it was an hell but now it's Exhell.

When you read this small doc you can listen to <a href="https://www.youtube.com/watch?v=lFbnQQ6t98Q" target="_blank">that</a>


# Getting Started

In your Maven/Gradle project, first add the corresponding dependency:

```xml
<dependency>
    <groupId>io.github.erdemoden</groupId>
    <artifactId>Exhell</artifactId>
    <version>1.0.1</version>
</dependency>
```
# Documentation 

  ## Annotations
  the title is Annotations but actually there is only one annotation in this library for now. It's called HellIndex 

  An example ussage of that annotation is like that 

  ``` java
public class CutService {
    @HellIndex(hellColumnOrder = 1)
    private String cutId;
    @HellIndex(hellColumnOrder = 2)
    private Integer cutAmount;
    @HellIndex(hellColumnOrder = 3,hellRowOrder = 1)
    private String name;
    @HellIndex(hellColumnOrder = 4,hellRowOrder = 2)
    private String surname;
    @HellIndex(hellColumnOrder = 5,hellRowOrder = 80)
    private String address;
}
  ```
The code above is actually clear enough but I am going to explain it too.

the HellIndex annotation has 2 elements one is hellColumnOrder the other one is hellRowOrder.
you can put this annotations to ElementType Fields. You do not have to write where to start rows with hellRowOrder the default value of that is 0 
but you have to write where to start the columns so you should write hellColumnOrder to specify where the columns should start.

# Builder
  
  There are 12 builder method in Exhell right now there will be more.

  
  **List of Methods :** 

  
  **withFileName(String fileName),** 
  
  
   **withBackgroundColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color),**
  
  
  **withBackgroundColorByCell(Integer hellCell, IndexedColors color),**
  
  
  **withFontColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color),**
  
  
  **withFontColorByCell(Integer hellCell, IndexedColors color),**
  
  
  **withBorderColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color),**
  
  
  **withBorderColorByCell(Integer hellCell, IndexedColors color),**
  
  
  **withBoldByRowAndCell(Integer hellCell, Integer hellRow),**
  
  
  **withBoldByCell(Integer hellRow),**
  
  
  **withItalicByRowAndCell(Integer hellCell, Integer hellRow, Boolean isItalic),**
  
  
  **withItalicByCell(Integer hellCell),**
  
  
  **writeToExHell(List<T> objects)**

  
  if you do not believe me you can count the methods it should be 12 of them 

  I do not explain every method one by one because it's boring and also the method names clear enough but I will explain some of them and make an example with them

  First of all the **withFileNames** method you do not have to use it if you use it the filename will be the String you given + xlsx but if you do not put that method the default fileName is file.xlsx 
  too creative right ? 

 There are also some style methods like **withBackgroundColorByRowAndCell** and **withBackgroundColorByCell**. If you notice, every style method has both the ByRowAndCell and ByCell variants. With the ByRowAndCell methods, you specify the index where the style will be applied. These methods apply the style to a specific index, affecting only one cell. On the other hand, with the ByCell methods, you can apply the style to the entire column, not just a single element. For example, if you choose column 5, the style will be applied to all the elements in column 5.


 # Example

 ``` java
HellOptions.builder()
                .withFileName("HellDocExample")
                .withBackgroundColorByRowAndCell(2,0, IndexedColors.BLUE)
                .withFontColorByRowAndCell(2,0, IndexedColors.GREEN)
                .withBorderColorByRowAndCell(2, 0,IndexedColors.RED)
                .withFontColorByCell(3, IndexedColors.RED)
                .withBoldByCell(3)
                .withFontColorByRowAndCell(3,1254,IndexedColors.RED)
                .withBorderColorByRowAndCell(3, 1254,IndexedColors.BLACK)
                .withBackgroundColorByRowAndCell(3, 1254, IndexedColors.AQUA)
                .withItalicByRowAndCell(3, 1254,true)
                .withBackgroundColorByCell(3, IndexedColors.YELLOW)
                .withBorderColorByCell(3, IndexedColors.DARK_BLUE)
                .withFontColorByCell(4, IndexedColors.RED)
                .writeToExHell(cutServiceList);
 ```
I tried above example and you can download it [here](https://docs.google.com/spreadsheets/d/14qdjq4uuvqmOCDUiHcw36f547kr2EBuA/edit?usp=sharing&ouid=100163933498366054655&rtpof=true&sd=true) 

I also explain it with pictures too. I use `CutService` DTO I shared above with same annotations.


![Google Drive Resmi](https://drive.google.com/uc?id=1oxzFWY13Uu_Z-V_j7Dg3qAwve57G14zb)

As you can see in the example image above, the columns do not start from index 0 because, in the `CutService` DTO, we define the indexes from 1 to 5.

Additionally, column 3 (which is column D) starts from row 1, not 0, because in the `CutService` DTO, we set `hellRowOrder = 1`.

Column E behaves similarly because in the `CutService` DTO, we define `@HellIndex(hellColumnOrder = 4, hellRowOrder = 2)`.

There is no element in column F because in the `CutService` DTO, we specify `@HellIndex(hellColumnOrder = 5, hellRowOrder = 80)`, meaning we will see elements in row 80 for column 5.

Also, you can see that all the elements in column 4 have a red font color because, using the builder, we wrote:
```java
withFontColorByCell(4, IndexedColors.RED)
```
![Google Drive Resmi](https://drive.google.com/uc?id=1mcN3jiAQxcQfiqJJPf0LxS9AOTs3YRIu)

As you can see in the picture above, we successfully implemented the styles in column 1254, row 3. In the builder, we declare them 

like this:

 ```java 
.withFontColorByRowAndCell(3,1254,IndexedColors.RED)
                .withBorderColorByRowAndCell(3, 1254,IndexedColors.BLACK)
                .withBackgroundColorByRowAndCell(3, 1254, IndexedColors.AQUA)
                .withItalicByRowAndCell(3, 1254,true)
 ```

Additionally, I would like to mention that, since we have reached row 80, we can now see the data in column 5 as well, which is visible in the image


So this is Exhell library Enjoy ! 

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Ferdemoden%2FExhell.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Ferdemoden%2FExhell?ref=badge_large)
