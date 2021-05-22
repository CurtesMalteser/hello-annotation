package com.curtesmalteser.helloannotations

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_11)
class HelloProcessor: AbstractProcessor() {
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        TODO("Not yet implemented")
    }


}