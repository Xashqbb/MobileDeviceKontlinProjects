package com.example.laba_13

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NoteRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: NoteRepository
    private val mockDao = mockk<NoteDao>()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = NoteRepository(mockDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addNote inserts note into database`() = runBlocking {
        val testNote = Note(id = 1, title = "Test Note", description = "This is a test note")

        coEvery { mockDao.insert(testNote) } just Runs

        repository.addNote(testNote)

        coVerify { mockDao.insert(testNote) }
    }

    @Test
    fun `updateNote updates note in database`() = runBlocking {
        val testNote = Note(id = 1, title = "Updated Note", description = "This is updated")

        coEvery { mockDao.update(testNote) } just Runs

        repository.updateNote(testNote)

        coVerify { mockDao.update(testNote) }
    }

    @Test
    fun `deleteNote removes note from database`() = runBlocking {
        val testNote = Note(id = 1, title = "Test Note", description = "To be deleted")

        coEvery { mockDao.delete(testNote) } just Runs

        repository.deleteNote(testNote)

        coVerify { mockDao.delete(testNote) }
    }

    @Test
    fun `getAllNotes returns list of notes`() {
        val testNotes = listOf(
            Note(id = 1, title = "Note 1", description = "Description 1"),
            Note(id = 2, title = "Note 2", description = "Description 2")
        )

        every { mockDao.getAllNotes() } returns MockLiveData(testNotes)

        val notes = repository.getAllNotes().value

        assertNotNull(notes)
        assertEquals(2, notes?.size)
        assertEquals("Note 1", notes?.get(0)?.title)
    }

    @Test
    fun `searchNotes returns filtered notes`() {
        val query = "test"
        val testNotes = listOf(
            Note(id = 1, title = "Test Note", description = "Description"),
            Note(id = 2, title = "Another Note", description = "Description")
        )

        every { mockDao.searchNotes(query) } returns MockLiveData(testNotes)

        val notes = repository.searchNotes(query).value

        assertNotNull(notes)
        assertEquals(2, notes?.size)
    }
}
