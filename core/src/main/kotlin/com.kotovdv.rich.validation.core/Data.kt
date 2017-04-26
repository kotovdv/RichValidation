import annotations.Verify

@Verify(expression = "firstField > secondField")
@Verify(expression = "secondField < thirdField")
class Data(val firstField: Long,
           val secondField: Long,
           val thirdField: Long)