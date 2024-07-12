package com.genxsol.home.domain.usecase

import com.genxsol.home.domain.model.HomeSections
import kotlinx.coroutines.flow.Flow

interface GetInitialHomeUseCase {
    fun getInitialHome(): Flow<HomeSections>
}