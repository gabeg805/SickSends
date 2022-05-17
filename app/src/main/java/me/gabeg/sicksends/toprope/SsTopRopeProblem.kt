package me.gabeg.sicksends.toprope

import androidx.room.Entity
import me.gabeg.sicksends.rope.SsRopeProblem

/**
 * Aspects of a top rope problem that are saved.
 */
@Entity(tableName = "top_rope")
class SsTopRopeProblem : SsRopeProblem()
