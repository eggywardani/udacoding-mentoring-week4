<?php
include_once("koneksi.php");



if (!empty($_POST['id'])) {
    $id = $_POST['id'];

    $query = "DELETE FROM pengunjung WHERE id = '$id'";

    $delete = mysqli_query($connect, $query);

    if ($delete) {
        setResponse(true, "Success delete data");
    } else {
        setResponse(false, "Failed delete data");
    }
} else {
    setResponse(false, "ID harus diisi");
}



function setResponse($isSuccess, $message)
{
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,

    );
    echo json_encode($result);
}
