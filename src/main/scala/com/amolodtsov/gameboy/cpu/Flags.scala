package com.amolodtsov.gameboy.cpu

import BitUtils._

class Flags {
  private val Z_POS = 7
  private val N_POS = 6
  private val H_POS = 5
  private val C_POS = 4

  private var flags: Int = 0

  def getFlagsByte(): Int = {
    flags
  }

  def setFlagsByte(flags: Int): Unit = {
    checkByteArgument("flags", flags)
    this.flags = flags
  }

  def isZ(): Boolean = getBit(flags, Z_POS)

  def isN(): Boolean = getBit(flags, N_POS)

  def isH(): Boolean = getBit(flags, H_POS)

  def isC(): Boolean = getBit(flags, C_POS)

  def setZ(z: Boolean): Unit = setBit(flags, Z_POS, z)

  def setN(n: Boolean): Unit = setBit(flags, N_POS, n)

  def setH(h: Boolean): Unit = setBit(flags, H_POS, h)

  def setC(c: Boolean): Unit = setBit(flags, C_POS, c)

  def clearZ(): Unit = clearBit(flags, Z_POS)

  def clearN(): Unit = clearBit(flags, N_POS)

  def clearH(): Unit = clearBit(flags, H_POS)

  def clearC(): Unit = clearBit(flags, C_POS)

  override def toString: String = {
    val result: StringBuilder = new StringBuilder()
    result
      .append(if (isZ()) {
        'Z'
      } else {
        '-'
      })
      .append(if (isN()) {
        'N'
      } else {
        '-'
      })
      .append(if (isH()) {
        'H'
      } else {
        '-'
      })
      .append(if (isC()) {
        'C'
      } else {
        '-'
      })
      .append("----");
    result.toString()
  }
}
