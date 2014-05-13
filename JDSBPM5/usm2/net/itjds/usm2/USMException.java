/**
 * 
 */
package net.itjds.usm2;

import java.io.PrintStream;
import java.io.PrintWriter;

public class USMException extends Exception {


	// 事件相关异常
	public static final int WHERE = 1;

	public static final int DELETE = 2;

	public static final int UPDATE = 32;

	public static final int CONNECTION = 33;


	
	public static final int FORMNOTFONUD = 	9000;

	/** Exception that might have caused this one. */
	private Throwable cause;

	/** Exception code that defined in BPM. */
	private int errorCode;

	/**
	 * Constructs a build exception with no descriptive information.
	 */
	public USMException() {
		super();
	}

	/**
	 * Constructs an exception with the given descriptive message and error
	 * code.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 * @param errorCode
	 *            Error code defined in BPM.
	 */
	public USMException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs an exception with the given descriptive message and error
	 * code.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 * @param cause
	 *            The exception that might have caused this one. May be
	 *            <code>null</code>.
	 * @param errorCode
	 *            Error code defined in BPM.
	 */
	public USMException(String message, Throwable cause, int errorCode) {
		this(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs an exception with the given descriptive message.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 */
	public USMException(String message) {
		super(message);
	}

	/**
	 * Constructs an exception with the given message and exception as a root
	 * cause.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code> unless a cause is specified.
	 * @param cause
	 *            The exception that might have caused this one. May be
	 *            <code>null</code>.
	 */
	public USMException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	/**
	 * Constructs an exception with the given exception as a root cause.
	 * 
	 * @param cause
	 *            The exception that might have caused this one. Should not be
	 *            <code>null</code>.
	 */
	public USMException(Throwable cause) {
		super(cause.toString());
		this.cause = cause;
	}

	/**
	 * Retrieves the BPM exception code for this <code>BPMException</code>
	 * object.
	 * 
	 * @return the vendor's error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Returns the nested exception, if any.
	 * 
	 * @return the nested exception, or <code>null</code> if no exception is
	 *         associated with this one
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * Returns the nested exception, if any.
	 * 
	 * @return the nested exception, or <code>null</code> if no exception is
	 *         associated with this one
	 */
	public Throwable getException() {
		return cause;
	}

	/**
	 * Returns the location of the error and the error message.
	 * 
	 * @return the location of the error and the error message
	 */
	public String toString() {
		return getMessage();
	}

	/**
	 * Prints the stack trace for this exception and any nested exception to
	 * <code>System.err</code>.
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * Prints the stack trace of this exception and any nested exception to the
	 * specified PrintStream.
	 * 
	 * @param ps
	 *            The PrintStream to print the stack trace to. Must not be
	 *            <code>null</code>.
	 */
	public void printStackTrace(PrintStream ps) {
		synchronized (ps) {
			if (errorCode != 0) {
				ps.println("Error Code: " + errorCode);
			}
			super.printStackTrace(ps);
			if (cause != null) {
				ps.println("--- Nested Exception ---");
				cause.printStackTrace(ps);
			}
		}
	}

	/**
	 * Prints the stack trace of this exception and any nested exception to the
	 * specified PrintWriter.
	 * 
	 * @param pw
	 *            The PrintWriter to print the stack trace to. Must not be
	 *            <code>null</code>.
	 */
	public void printStackTrace(PrintWriter pw) {
		synchronized (pw) {
			if (errorCode != 0) {
				pw.println("Error Code: " + errorCode);
			}
			super.printStackTrace(pw);
			if (cause != null) {
				pw.println("--- Nested Exception ---");
				cause.printStackTrace(pw);
			}
		}
	}

}
