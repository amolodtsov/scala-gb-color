package com.amolodtsov.gameboy

import com.amolodtsov.gameboy.cpu.{Cpu, Opcodes}
import com.amolodtsov.gameboy.memory.Mmu
import org.scalatest.{FlatSpec, Matchers}

class GameboyTest  extends FlatSpec with Matchers {

  "Gameboy" should "boot application" in {
    Opcodes.init()
    val mmu:Mmu = new Mmu()
    val cpu:Cpu = new Cpu(mmu)
    while (true) {
      cpu.runCommand()
    }
  }

}
