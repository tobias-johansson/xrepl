package org.xtext.scripting.tests;

import com.google.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper.Result;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xtext.scripting.ScriptingInjectorProvider;

@RunWith(value = XtextRunner.class)
@InjectWith(value = ScriptingInjectorProvider.class)
@SuppressWarnings("all")
public class ExecutionTest {
  @Inject
  private CompilationTestHelper _compilationTestHelper;
  
  @Inject
  private ReflectExtensions _reflectExtensions;
  
  @Test
  public void testHelloWorld() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("throw new Exception(\'Hello World\')");
    _builder.newLine();
    this.compileAndExecuteMainAndExpect(_builder, "Hello World");
  }
  
  @Test
  public void testDeepThought() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val answer = 2 * (17 + 4)");
    _builder.newLine();
    _builder.append("throw new Exception(answer.toString)");
    _builder.newLine();
    this.compileAndExecuteMainAndExpect(_builder, Integer.valueOf(42));
  }
  
  protected void compileAndExecuteMainAndExpect(final CharSequence script, final Object expectedResult) {
    final Procedure1<Result> _function = new Procedure1<Result>() {
        public void apply(final Result it) {
          try {
            try {
              Class<? extends Object> _compiledClass = it.getCompiledClass();
              Object _newInstance = _compiledClass.newInstance();
              ExecutionTest.this._reflectExtensions.invoke(_newInstance, "main", null);
              Assert.fail("Expected ResultException not thrown.");
            } catch (final Throwable _t) {
              if (_t instanceof InvocationTargetException) {
                final InvocationTargetException exc = (InvocationTargetException)_t;
                String _string = expectedResult.toString();
                Throwable _cause = exc.getCause();
                String _message = _cause.getMessage();
                Assert.assertEquals(_string, _message);
              } else {
                throw Exceptions.sneakyThrow(_t);
              }
            }
          } catch (Exception _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
    this._compilationTestHelper.compile(script, new IAcceptor<Result>() {
        public void accept(Result t) {
          _function.apply(t);
        }
    });
  }
}
