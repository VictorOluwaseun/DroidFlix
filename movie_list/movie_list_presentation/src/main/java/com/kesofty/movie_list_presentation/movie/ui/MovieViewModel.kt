package com.kesofty.movie_list_presentation.movie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kesofty.core.util.Result
import com.kesofty.movie_list_domain.model.Movie
import com.kesofty.movie_list_domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    init {
        Log.i("MovieViewModel", "===>ViewModel started for MovieViewModel===>")
    }

//        private val _movieList = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))


//    fun getMoveList() {
//        viewModelScope.launch {
//            val movieL = movieUseCases.getMovieList() //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = null)
//            _movieList.emitAll(movieL)
//        }
//    }

//    val movieL = movieUseCases.getMovieList().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = null)

    val movieList: StateFlow<Result<List<Movie>>> = flow {
        val movieL = movieUseCases.getMovieList()
        emitAll(movieL)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = Result.Success(emptyList()))

    val lowF = flow {
        emit("1")
    }

    private val _state = MutableStateFlow("this")
    val state = _state.asStateFlow()

    private val _shared = MutableSharedFlow<String>()
    val shared = _shared.asSharedFlow()

//    fun getMovieList() {
//        viewModelScope.launch {
//            _movieList.emit()
//        }
//    }

    fun getSh(){
//        _shared.emit("that")
        _state.value
    }

}