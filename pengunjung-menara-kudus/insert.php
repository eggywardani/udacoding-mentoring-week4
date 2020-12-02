<?php
include_once("koneksi.php");

if (!empty($_POST['nama']) && !empty($_POST['nohp']) && !empty($_POST['asal_kota']) && !empty($_POST['alamat']) && !empty($_POST['tanggal_kunjungan'])) {
    $nama = $_POST['nama'];
    $nohp = $_POST['nohp'];
    $asal_kota = $_POST['asal_kota'];
    $alamat = $_POST['alamat'];
    $tanggal_kunjungan = $_POST['tanggal_kunjungan'];



    $query = "INSERT INTO pengunjung(nama, nohp, asal_kota, alamat, tanggal_kunjungan) VALUES ('$nama','$nohp', '$asal_kota','$alamat', '$tanggal_kunjungan')";
    $insert = mysqli_query($connect, $query);

    if ($insert) {
        setResponse(true, "Success Insert Data");
    } else {
        setResponse(false, "Failed Insert Data");
    }
} else {
    setResponse(false, "Nama, Nomor hp, asal kota, alamat dan tanggal kunjungan harus diisi");
}


function setResponse($isSuccess, $message)
{
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,

    );
    echo json_encode($result);
}
