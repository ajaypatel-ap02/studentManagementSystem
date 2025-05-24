import React from "react";
import { useParams } from "react-router-dom";

function DeleteStudent() {
  const { id } = useParams();
  return <div>Delete Student with ID: {id}</div>;
}

export default DeleteStudent;
