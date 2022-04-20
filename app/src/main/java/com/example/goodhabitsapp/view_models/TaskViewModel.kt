package com.example.goodhabitsapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodhabitsapp.data.repository.DataStoreRepository
import com.example.goodhabitsapp.data.repository.TaskRepository
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.domain.model.Task
import com.example.goodhabitsapp.util.Action
import com.example.goodhabitsapp.util.Constants.MAX_TITLE_LENGTH
import com.example.goodhabitsapp.util.RequestState
import com.example.goodhabitsapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
        private val repository: TaskRepository,
         private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)


    val searchAppBarState: MutableState<SearchAppBarState> =
       mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> =
        mutableStateOf("")

    private var _searchTask = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val searchTask: StateFlow<RequestState<List<Task>>> = _searchTask

    fun searchDatabase(searchQuery: String) {
        _searchTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery = "%$searchQuery%")
                    .collect { searchTask ->
                        _searchTask.value = RequestState.Success(searchTask)
                    }
            }

        } catch (e: Exception) {
            _searchTask.value = RequestState.Error(error = e)
        }
       searchAppBarState.value = SearchAppBarState.TRIGGERED

    }

    val lowPriorityTask: StateFlow<List<Task>> =
        repository.sortByLowPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            emptyList()
        )
    val highPriorityTask: StateFlow<List<Task>> =
        repository.sortByHighPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private var _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<Priority>> = _sortState

    fun readSortState() {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
               dataStoreRepository.readSortState
                    .map { Priority.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }

        } catch (e: Exception) {
            _sortState.value = RequestState.Error(error = e)
        }
    }

    fun persistSortState(priority: Priority) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistSortState(priority = priority)
        }
    }

    private var _allTask = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTask: StateFlow<RequestState<List<Task>>> = _allTask

    fun getAllTask() {
        _allTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTask.collect { allTask ->
                    _allTask.value = RequestState.Success(allTask)
                }
            }

        } catch (e: Exception) {
            _allTask.value = RequestState.Error(error = e)
        }

    }

    private var _selectedTask: MutableStateFlow<Task?> = MutableStateFlow(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = Task(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask = toDoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = Task(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.updateTask(toDoTask = toDoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = Task(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.deleteTask(toDoTask = toDoTask)
        }
    }

    private fun deleteAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTask()
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {
                updateTask()
            }
            Action.DELETE -> {
                deleteTask()
            }
            Action.DELETE_ALL -> {
                deleteAllTask()
            }
            Action.UNDO -> {
                addTask()
            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    fun updateTaskFields(selectedTask: Task?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }

    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

}