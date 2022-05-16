package me.gabeg.sicksends.boulder

import androidx.room.Entity
import me.gabeg.sicksends.problem.SsProblem

/**
 * Aspects of a boulder problem that are saved.
 */
@Entity(tableName = "boulder")
class SsBoulder : SsProblem()
