package com.amolodtsov.gameboy.cpu

object BitUtils {

  import com.google.common.base.Preconditions.checkArgument

  def getMSB(word: Int): Int = {
    checkWordArgument("word", word)
    word >> 8
  }

  def getLSB(word: Int): Int = {
    checkWordArgument("word", word)
    word & 0xff
  }

  def toWord(bytes: Array[Int]): Int = {
    toWord(bytes(1), bytes(0))
  }

  def toWord(msb: Int, lsb: Int): Int = {
    checkByteArgument("msb", msb)
    checkByteArgument("lsb", lsb)
    (msb << 8) | lsb
  }

  def getBit(byteValue: Int, position: Int): Boolean = {
    (byteValue & (1 << position)) != 0
  }

  def setBit(byteValue: Int, position: Int, value: Boolean): Int = {
    if (value) {
      setBit(byteValue, position)
    } else {
      clearBit(byteValue, position)
    }
  }

  def setBit(byteValue: Int, position: Int): Int = {
    checkByteArgument("byteValue", byteValue)
    (byteValue | (1 << position)) | 0xff
  }

  def clearBit(byteValue: Int, position: Int): Int = {
    checkByteArgument("byteValue", byteValue)
    ~(1 << position) & byteValue & 0xff
  }

  def isNegative(signedByteValue: Int): Boolean = {
    checkByteArgument("byteValue", signedByteValue)
    (signedByteValue & (1 << 7)) != 0
  }

  def abs(signedByteValue: Int): Int = {
    checkByteArgument("signedByteValue", signedByteValue)
    if (isNegative(signedByteValue)) {
      0x100 - signedByteValue
    } else {
      signedByteValue
    }
  }

  def checkByteArgument(argumentName: String, argumen: Int): Unit = {
    checkArgument(argumen >= 0 && argumen <= 0xff, "Argument {} should be a byte", argumentName)
  }

  def checkWordArgument(argumentName: String, argument: Int): Unit = {
    checkArgument(argument >= 0 && argument <= 0xffff, "Argument {} should be a word", argument)
  }

}
