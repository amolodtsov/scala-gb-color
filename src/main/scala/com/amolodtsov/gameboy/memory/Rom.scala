package com.amolodtsov.gameboy.memory

import com.amolodtsov.gameboy.AddressSpace
import org.slf4j.{Logger, LoggerFactory}

class Rom(space: Array[Int], offset: Int) extends AddressSpace {

  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  override def setByte(address: Int, value: Int): Unit = log.warn("Can't write {} to ROM {}", value, address)

  override def getByte(address: Int): Int = {
    if (offset > address) {
      log.warn("Address {} < offset {}", address, offset)
      0
    } else {
      val position: Int = address - offset
      if (position >= space.length) {
        log.warn("Address {} out of ROM space {}", address, offset + space.length)
        0
      } else {
        space(position)
      }
    }
  }
}
