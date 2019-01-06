package com.amolodtsov.gameboy.cpu

import BitUtils._

class Registers {

  private var a, b, c, d, e, f, h, l: Int = 0
  private var sp: Int = 0
  private var pc: Int = 0

  private val flags: Flags = new Flags()

  private var ime:Boolean = true

  def getA(): Int = a

  def getB(): Int = b

  def getC(): Int = c

  def getD(): Int = d

  def getE(): Int = e

  def getF(): Int = f

  def getH(): Int = h

  def getL(): Int = l

  def getAF(): Int = a << 8 | f

  def getBC(): Int = b << 8 | c

  def getDE(): Int = d << 8 | e

  def getHL(): Int = h << 8 | l

  def getSP(): Int = sp

  def getPC(): Int = pc

  def getFlags():Flags = flags

  def isIME():Boolean = ime

  def setA(a: Int): Unit = {
    checkByteArgument("a", a)
    this.a = a
  }

  def setB(b: Int): Unit = {
    checkByteArgument("b", b)
    this.b = b
  }

  def setC(c: Int): Unit = {
    checkByteArgument("c", c)
    this.c = c
  }

  def setD(d: Int): Unit = {
    checkByteArgument("d", d)
    this.d = d
  }

  def setE(e: Int): Unit = {
    checkByteArgument("e", e)
    this.e = e
  }

  def setF(f: Int): Unit = {
    checkByteArgument("f", f)
    this.f = f
  }

  def setH(h: Int): Unit = {
    checkByteArgument("h", h)
    this.h = h
  }

  def setL(l: Int): Unit = {
    checkByteArgument("l", l)
    this.l = l
  }

  def setAF(af: Int): Unit = {
    checkWordArgument("af", af)
    a = getMSB(af)
    f = getLSB(af)
  }

  def setBC(bc: Int): Unit = {
    checkWordArgument("bc", bc)
    b = getMSB(bc)
    c = getLSB(bc)
  }

  def setDE(de: Int): Unit = {
    checkWordArgument("de", de)
    d = getMSB(de)
    e = getLSB(de)
  }

  def setHL(hl: Int): Unit = {
    checkWordArgument("hl", hl)
    h = getMSB(hl)
    l = getLSB(hl)
  }

  def setSP(sp: Int): Unit = {
    checkWordArgument("sp", sp)
    this.sp = sp
  }

  def setPC(pc: Int): Unit = {
    checkWordArgument("pc", pc)
    this.pc = pc
  }

  def setIME(ime:Boolean):Unit = {
    this.ime =ime
  }

  def decrementHL():Int = {
    val oldHL:Int = getHL()
    setHL((oldHL - 1) % 0xffff)
    oldHL
  }

  def incrementHL():Int = {
    val oldHL:Int = getHL()
    setHL((oldHL + 1) % 0xffff)
    oldHL
  }

  def decrementSP():Unit = {
    sp = (sp -1 ) % 0xffff
  }

  def incrementSP():Unit = {
    sp = (sp + 1) % 0xffff
  }

  def addToPC(signedByte:Int):Unit = {
    checkByteArgument("signedByte", signedByte)
    if(isNegative(signedByte)) {
      pc = (pc -abs(signedByte) ) & 0xffff
    } else {
      pc = (pc + abs(signedByte)) & 0xffff
    }
  }

//  override def toString: String = String.format("AF=%04x, BC=%04x, DE=%04x, HL=%04x, SP=%04x, PC=%04x, %s", getAF(), getBC(), getDE(), getHL(), getSP(), getPC(), getFlags().toString)
}
