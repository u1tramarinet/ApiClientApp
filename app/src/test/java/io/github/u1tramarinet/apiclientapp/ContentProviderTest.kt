package io.github.u1tramarinet.apiclientapp

import android.content.ContentResolver
import android.database.Cursor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ContentProviderTest {
    @MockK
    lateinit var cursor: Cursor

    @MockK
    lateinit var contentResolver: ContentResolver

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { cursor.moveToFirst() } returns true
        every { cursor.getColumnIndex(any()) } returns 0
        every { cursor.getString(any()) } returns "test"
    }

    @Test
    fun test() {
        every { contentResolver.query(any(), any(), any(), any(), any()) } returns mockk()
    }
}