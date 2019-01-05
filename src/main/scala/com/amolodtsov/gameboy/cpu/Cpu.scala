package com.amolodtsov.gameboy.cpu

import com.amolodtsov.gameboy.AddressSpace
import com.amolodtsov.gameboy.cpu.Command.Operation

import scala.collection.mutable.MutableList

class Cpu(addressSpace: AddressSpace) {

  val registers: Registers = new Registers()

  private val commands:MutableList[Command] = new MutableList[Command]()
  regCmd(commands, 0x06, 8 , 1, "LD B, {}", (r, m, a) => r.setB(a(0)))
  regCmd(commands, 0x0e, 8 , 1, "LD C, {}", (r, m, a) => r.setC(a(0)))
  regCmd(commands, 0x16, 8 , 1, "LD D, {}", (r, m, a) => r.setD(a(0)))
  regCmd(commands, 0x1e, 8 , 1, "LD E, {}", (r, m, a) => r.setE(a(0)))
  regCmd(commands, 0x26, 8 , 1, "LD H, {}", (r, m, a) => r.setH(a(0)))
  regCmd(commands, 0x2e, 8 , 1, "LD L, {}", (r, m, a) => r.setL(a(0)))

  private def regCmd(commands: MutableList[Command], opcode: Int, cycles: Int, argsLength: Int, label: String, operation: Operation): Unit = {
    commands(opcode) = new Command(opcode, cycles, argsLength, label, operation)
  }
}
