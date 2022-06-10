package ba.etf.rma22.projekat.data.repositories



import ba.etf.rma22.projekat.data.models.*
import retrofit2.http.*

interface Api {
    //Istrazivanje
    @GET("istrazivanje")
    suspend fun getSveIstrazivanja(@Query("offset") offset : Int):List<Istrazivanje>

    @GET("istrazivanje/{id}")
    suspend fun getIstrazivanje(@Path("id") idIstrazivanje : Int) : Istrazivanje

    @GET("grupa/{gid}/istrazivanje")
    suspend fun getIstrazivanjaZaGrupu(@Path("gid") idGRupe : Int) : Istrazivanje


    //GRUPA
    @GET("anketa/{id}/grupa")
    suspend fun getGrupeDostupnaAnketa(@Path("id") idAnketa : Int) : List<Grupa>

    @POST("grupa/{gid}/student/{id}")
    suspend fun upisiStudentaNaGrupu(@Path("gid") gid:Int, @Path("id") idStudenta:String) : Message

    @GET("student/{id}/grupa")
    suspend fun getGrupeUKojeJeUpisan(@Path("id") idStudenta : String) : List<Grupa>

    @GET("grupa")
    suspend fun getSveGrupe() : List<Grupa>

    @GET("grupa/{id}")
    suspend fun getGrupa(@Path("id") idGrupe : Int) : Grupa



    //ANKETA
    @GET("anketa")
    suspend fun getSveAnekte() : List<Anketa>

    @GET("anketa")
    suspend fun getOffsetAnekte(@Query("offset") offset: Int) : List<Anketa>

    @GET("anketa/{id}")
    suspend fun getAnketa(@Path("id") idAnketa : Int) : Anketa

    @GET("grupa/{id}/ankete")
    suspend fun getAnketaZaGrupu(@Path("id") idGrupe : Int) : List<Anketa>

    //ODGOVOR
    @GET("student/{id}/anketataken/{ktid}/odgovori")
    suspend fun getOdgovoriAnkete(@Path("ktid") idAnketaTakena : Int, @Path("id") idStudenta : String) : List<Odgovor>


    @POST("student/{id}/anketataken/{ktid}/odgovor")
    suspend fun dodajOdgovorZaPokusaj(@Path("id") idStudenta:String, @Path("ktid") kvizTakenId:Int, @Body odgovor : OdgovorPitanje)

    //ANKETATAKEN
    @GET("student/{id}/anketataken")
    suspend fun getPokusajeZaStudenta(@Path("id")idStudenta : String) : List<AnketaTaken>

    @POST("student/{id}/anketa/{kid}")
    suspend fun zapocniRjesavanjeAnkete( @Path("id") idStudenta : String, @Path("kid") idAnketa : Int) : AnketaTaken

    //PITANJE
    @GET("anketa/{id}/pitanja")
    suspend fun getPitanja(@Path("id") idAnketa : Int) : List<Pitanje>

}