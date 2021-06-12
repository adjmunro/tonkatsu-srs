package nz.tonkatsu.core.util

import android.content.Context
import android.os.Environment.*
import android.util.Log
import android.widget.Toast
import nz.tonkatsu.core.R
import java.io.File
import java.io.IOException

/**
 * Storage utils manages reading and writing files to internal and external storage.
 *
 * @constructor Create empty Storage utils
 */
object StorageUtils {

    /**
     * T a g object name for Log
     */
    const val TAG: String = "StorageUtils"

    /**
     * Create or get file
     *
     * Note: A File object actually represents a path, not the file itself
     *
     * @param destination storage location              e.g. /storage/emulated/0/Android/data
     * @param fileName name of file                     e.g. MyFile.xml
     * @param directoryName directory in storage home   e.g. ./saved_forms/
     * @return fully assimilated File path
     */
    private fun createOrGetFile(
        destination: File,
        fileName: String,
        directoryName: String,
    ): File = File(File(destination, directoryName), fileName)

    /**
     * Get text from storage
     *
     * @param context context
     * @param root storage home path
     * @param fileName file name
     * @param directoryName local storage directory
     * @return text read from the requested file
     */
    fun getTextFromStorage(
        context: Context,
        root: File,
        fileName: String,
        directoryName: String
    ): String? = read(context, createOrGetFile(root, fileName, directoryName))

    /**
     * Is external storage readable
     *
     * @return true if application can read from storage
     */
    fun isExternalStorageReadable(): Boolean =
        getExternalStorageState() in listOf(MEDIA_MOUNTED, MEDIA_MOUNTED_READ_ONLY)

    /**
     * Is external storage writable
     *
     * @return true if application can write to storage
     */
    fun isExternalStorageWritable(): Boolean = getExternalStorageState() == MEDIA_MOUNTED

    /**
     * Read from file
     *
     * @param context context
     * @param file file path
     * @return contents of [file] as string, or null if read failed
     */
    private fun read(context: Context, file: File): String? {
        val s = StringBuilder()

        if (file.exists()) {
            try {
                file.bufferedReader().useLines { lines ->
                    lines.forEach {
                        s.append(it)
                        s.append("\n")
                    }
                }
            } catch (e: IOException) {
                toast(context, context.getString(R.string.error_happened))
                return null
            }
        }

        return s.toString()
    }

    /**
     * Set text in storage
     *
     * @param context context
     * @param root storage home path
     * @param fileName file name
     * @param directoryName local storage directory
     * @param text text to write into file
     * @return true if write was successful
     */
    fun setTextInStorage(
        context: Context,
        root: File,
        fileName: String,
        directoryName: String,
        text: String
    ): Boolean = write(context, text, createOrGetFile(root, fileName, directoryName))

    /**
     * Toast message shorthand
     *
     * @param c context
     * @param msg message to toast
     * @param long true if display length should be long
     */
    private fun toast(c: Context, msg: String, long: Boolean = true) {
        Toast.makeText(c, msg, if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

    /**
     * Write to file
     *
     * @param context context
     * @param text string to write to [file]
     * @param file file path
     * @return true if write was successful
     */
    private fun write(context: Context, text: String, file: File): Boolean {
        return try {
            file.parentFile?.mkdirs()
            file.bufferedWriter().use { out -> out.write(text) }
            toast(context, context.getString(R.string.file_saved).format(file.path))
            Log.d(TAG, "File written successfully.\n${file.canonicalPath}")
            true
        } catch (e: IOException) {
            toast(context, context.getString(R.string.error_happened))
            Log.d(TAG, "An error occurred while exporting file!")
            e.printStackTrace()
            false
        }
    }

}