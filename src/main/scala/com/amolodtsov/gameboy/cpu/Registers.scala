package com.amolodtsov.gameboy.cpu

import BitUtils._

class Registers {

  private var a, b, c, d, e, f, h, l: Int = 0
  private var sp: Int = 0
  private var pc: Int = 0

  private val flags: Flags = new Flags()

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

  def getSp(): Int = sp

  def getPc(): Int = pc

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

  def setSp(sp: Int): Unit = {
    checkWordArgument("sp", sp)
    this.sp = sp
  }

  def setPc(pc: Int): Unit = {
    checkWordArgument("pc", pc)
    this.pc = pc
  }

}
