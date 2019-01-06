package com.amolodtsov.gameboy.memory

import com.amolodtsov.gameboy.AddressSpace

class Mmu extends AddressSpace {

  private val bootRom: AddressSpace = new Rom(BootRom.GAMEBOY_CLASSIC, 0)

  private val ram: AddressSpace = new Ram()

  override def setByte(address: Int, value: Int): Unit = getSpace(address).setByte(address, value)

  override def getByte(address: Int): Int = getSpace(address).getByte(address)

  def getSpace(address: Int): AddressSpace = {
    if (address >= 0x00 && address <= 0xff) {
      bootRom
    } else {
      ram
    }
  }
}
