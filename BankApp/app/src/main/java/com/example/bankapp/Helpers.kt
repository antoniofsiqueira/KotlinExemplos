package com.example.bankapp

import android.content.Context
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

//Mensagens validação
fun Context.exibirMensagem(mensagem: String?) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}

// Valida CPF
fun isCpf(cpf : String) : Boolean{
    val cpfClean = cpf.replace(".", "").replace("-", "")

    //## check se o tamanho é 11
    if (cpfClean.length != 11)
        return false

    //## check se é numero
    try {
        val number  = cpfClean.toLong()
    }catch (e : Exception){
        return false
    }

    //continue
    var dvCurrent10 = cpfClean.substring(9,10).toInt()
    var dvCurrent11= cpfClean.substring(10,11).toInt()


    // A soma dos nove primeiros digitos determina o decimo digito
    val cpfNineFirst = IntArray(9)
    var i = 9
    while (i > 0 ) {
        cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
        i--
    }

    //Multiplica o nono digito para os tamanhos: 10,9..2
    var sumProductNine = IntArray(9)
    var weight = 10
    var position = 0
    while (weight >= 2){
        sumProductNine[position] = weight * cpfNineFirst[position]
        weight--
        position++
    }

    // Verifica o nono digito
    var dvForTenthDigit = sumProductNine.sum() % 11
    dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
    if(dvForTenthDigit > 9)
        dvForTenthDigit = 0
    if (dvForTenthDigit != dvCurrent10)
        return false

    // verifica o decimo digito
    var cpfTenFirst = cpfNineFirst.copyOf(10)
    cpfTenFirst[9] = dvCurrent10

    //multiple the nine digits for your weights: 10,9..2
    var sumProductTen = IntArray(10)
    var w = 11
    var p = 0
    while (w >= 2){
        sumProductTen[p] = w * cpfTenFirst[p]
        w--
        p++
    }
    //Verify the nineth digit
    var dvForeleventhDigit = sumProductTen.sum() % 11
    dvForeleventhDigit = 11 - dvForeleventhDigit
    //rule for tenth digit
    if(dvForeleventhDigit > 9)
        dvForeleventhDigit = 0
    if (dvForeleventhDigit != dvCurrent11)
        return false

    return true
}

// Valida E-mail
fun isEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// Comunicação
object Constantes {
    // login: https://bank-app-test.herokuapp.com/api/login
    // dados: https://bank-app-test.herokuapp.com/api/statements/{idUser}
    val web_service = "https://bank-app-test.herokuapp.com/api"
}

fun comunicacao(urlEnd: String, params: String): String {
    val sb = StringBuilder()
    val url: URL
    var conn: HttpURLConnection? = null
    try {
        url = URL(urlEnd)
        conn = url.openConnection() as HttpURLConnection
        //
        conn.requestMethod = "POST"
        conn.doInput = true
        conn.doOutput = true
        //
        //conn.setConnectTimeout(60000);
        //conn.setReadTimeout(60000);
        //
        val parametrosFormatados = StringBuilder()
        parametrosFormatados.append(URLEncoder.encode("json", "UTF-8"))
        parametrosFormatados.append("=")
        parametrosFormatados.append(URLEncoder.encode(params, "UTF-8"))
        //
        // Envio de Parametros
        val os = conn.outputStream
        val writer = BufferedWriter(
            OutputStreamWriter(os, "UTF-8")
        )

        writer.write(parametrosFormatados.toString())
        writer.flush()
        writer.close()
        //os.close();
        // Ler as informacoes enviados pelo Servidor
        sb.append(readStream(conn.inputStream))
    } catch (e: Exception) {
        sb.append(e.toString())
    } finally {
        conn?.disconnect()
    }
    return sb.toString()
}

private fun readStream(inputStream: InputStream): String {
    var reader: Reader? = null
    val writer = StringWriter()
    val buffer = CharArray(1024)
    try {
        reader = BufferedReader(
            InputStreamReader(inputStream, "UTF-8")
        )
        var n: Int = 0
        while (true) {
            n = reader.read(buffer)
            if (n != -1) {
                writer.write(buffer, 0, n)
            } else {
                break
            }
        }
    } catch (e: Exception) {
    } finally {
        if (reader != null) {
            try {
                reader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return writer.toString()
}


