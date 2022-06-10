package ba.etf.rma22.projekat.data.repositories

object AccountRepository {
        var acHash = "aa3328e0-6949-48d2-814e-80d7dd9a2a08"

        fun postaviHash(acHash : String) : Boolean{
            this.acHash=acHash;
            return true;
        }

        fun getHash(): String {
            return acHash
        }
}