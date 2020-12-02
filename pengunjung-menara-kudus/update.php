<?php
include_once("koneksi.php");

if (!empty($_POST['nama']) && !empty($_POST['nohp']) && !empty($_POST['asal_kota']) && !empty($_POST['alamat']) && !empty($_POST['tanggal_kunjungan'])) {
    $id = $_POST['id'];
    $nama = $_POST['nama'];
    $nohp = $_POST['nohp'];
    $asal_kota = $_POST['asal_kota'];
    $alamat = $_POST['alamat'];
    $tanggal_kunjungan = $_POST['tanggal_kunjungan'];


    $query = "UPDATE pengunjung set nama = '$nama', nohp = '$nohp', asal_kota = '$asal_kota', alamat ='$alamat', tanggal_kunjungan = '$tanggal_kunjungan' WHERE id= '$id'";
    $update = mysqli_query($connect, $query);

    if ($update) {
        setResponse(true, "Success Update Data");
    } else {
        setResponse(false, "Failed Update Data");
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
