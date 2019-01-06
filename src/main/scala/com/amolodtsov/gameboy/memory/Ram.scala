package com.amolodtsov.gameboy.memory

import com.amolodtsov.gameboy.AddressSpace

class Ram extends AddressSpace{

  private val space:Array[Int] = new Array[Int](0x10000)

  override def setByte(address: Int, value: Int): Unit = space(address) = value

  override def getByte(address: Int): Int = space(address)
}
