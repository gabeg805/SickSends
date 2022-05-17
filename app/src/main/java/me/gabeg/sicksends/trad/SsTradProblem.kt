package me.gabeg.sicksends.trad

import androidx.room.Entity
import me.gabeg.sicksends.rope.SsRopeProblem

/**
 * Aspects of a trad problem that are saved.
 */
@Entity(tableName = "trad")
class SsTradProblem : SsRopeProblem()
