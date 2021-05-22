package com.curtesmalteser.helloannotations

import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

class HelloProcessor: AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        mutableSetOf(HelloAnnotation::class.java.name)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun process(annotationSet: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        roundEnvironment?.getElementsAnnotatedWith(HelloAnnotation::class.java)
            ?.forEach {
                if (it.kind != ElementKind.CLASS) {
                    processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Only classes can be annotated")
                    return true
                }
                processAnnotation(it)
            }
        return false
    }

    private fun processAnnotation(element: Element) {
        val className = element.simpleName.toString()
        val elementPackage = processingEnv.elementUtils.getPackageOf(element).toString()


        val fileName = "Hello$className"

        val fileBuilder= FileSpec
            .builder(elementPackage, fileName)
            .addImport(
            "android.util", "Log"
        )

        val classBuilder = TypeSpec.classBuilder(fileName)

       classBuilder.addFunction(
           FunSpec
               .builder("logDebug")
               .addParameters(
                   listOf(
                       ParameterSpec("value", STRING)
                   )
               )
               .addCode(
                   """
                    |Log.d(this::class.java.name, value)
                    |""".trimMargin()
               )
               .build()
       )

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]

        kaptKotlinGeneratedDir?.let {

        fileBuilder
            .addType(classBuilder.build())
            .build()
            .writeTo(File(it))
        }

    }

}