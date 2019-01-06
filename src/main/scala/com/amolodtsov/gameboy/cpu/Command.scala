package com.amolodtsov.gameboy.cpu

import com.amolodtsov.gameboy.AddressSpace
import com.amolodtsov.gameboy.cpu.Command.Operation

class Command(opcode: Int, cycles: Int, argsLength: Int, label: String, operation: Operation) {

  def getArgsLength(): Int = argsLength

  def getOperation(): Operation = operation

  def getCycles(): Int = cycles

  def getLabel(): String = label

}

object Command {

  trait Operation {
    def run(registers: Registers, addressSpace: AddressSpace, arguments: Array[Int])
  }

}


