package com.amolodtsov.gameboy

trait AddressSpace {
  def setByte(address: Int, value: Int): Unit

  def getByte(address: Int): Int
}
