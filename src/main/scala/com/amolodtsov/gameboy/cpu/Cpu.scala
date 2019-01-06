package com.amolodtsov.gameboy.cpu

import com.amolodtsov.gameboy.AddressSpace
import com.amolodtsov.gameboy.cpu.Command.Operation
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable.MutableList
import jdk.nashorn.internal.objects.NativeArray.push

class Cpu(addressSpace: AddressSpace) {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  val registers: Registers = new Registers()

  def runCommand(): Int = {
    handleInterrupt()

    var pc:Int = registers.getPC()
    pc = pc+1
    var opcode:Int = addressSpace.getByte(pc)
    var cmd:Command = null
    if(opcode == 0xcb) {
      pc = pc +1
      opcode = addressSpace.getByte(pc)
      cmd = Opcodes.EXT_COMMANDS.get(opcode).get
    } else {
      cmd = Opcodes.COMMANDS.get(opcode).get
    }

    if (cmd == null) {
      log.warn("Invalid instruction %02x @ %04x", opcode, registers.getPC())
      0
    } else {
      val args:Array[Int] = new Array[Int](cmd.getArgsLength())
      for(i <- 0 to args.length) {
        pc = pc +1
        args(i) = addressSpace.getByte(pc)
      }

      if (log.isTraceEnabled) {
//        log.trace("%04x: %8s\t%s", registers.getPC(), getDump(registers.getPC(), pc), cmd.getLabel())
      }

      registers.setPC(pc)
      cmd.getOperation().run(registers, addressSpace, args)

      if(log.isTraceEnabled) {
        log.trace("Registers: {}", registers)
      }

      cmd.getCycles()
    }
  }

  def handleInterrupt(): Boolean = {
    if (!registers.isIME()) {
      false
    } else {
      val interruptFlug: Int = addressSpace.getByte(0xff0f)
      val interruptEnable: Int = addressSpace.getByte(0xffff)

      var handler: Int = 0
      if ((interruptEnable & interruptFlug & (1 << 0)) != 0) {
        handler = 0x0040 // V-Blank
      }
      if ((interruptEnable & interruptFlug & (1 << 1)) != 0) {
        handler = 0x0048 // LCDC Status
      }
      if ((interruptEnable & interruptFlug & (1 << 2)) != 0) {
        handler = 0x0050 // Timer Overflow
      }
      if ((interruptEnable & interruptFlug & (1 << 3)) != 0) {
        handler = 0x0058 // Serial Transfer
      }
      if ((interruptEnable & interruptFlug & (1 << 4)) != 0) {
        handler = 0x0060 // Hi-Lo of P10-P13
      }

      if (handler > 0) {
        registers.setIME(false)
        addressSpace.setByte(0xff0f, 0)
        push(registers.getPC())
        registers.setPC(handler)
        true
      } else {
        false
      }
    }
  }

  private def getDump(from:Int, to:Int):String = {
    val builder:StringBuilder = new StringBuilder()
//    for (i <- from to to ) {
//      builder.append(String.format("%02x", addressSpace.getByte(i) & 0xff))
//    }
    builder.toString()
  }
}
