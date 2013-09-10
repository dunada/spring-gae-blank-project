<c:if test="${flash_error != null}"> 
     <div class="alert alert-danger">
      <a class="close" data-dismiss="alert">×</a>
      <p>${flash_error}</p> 
      <p></p> 
    </div>
</c:if>


<c:if test="${flash_message != null}"> 
     <div class="alert alert-info">
      <a class="close" data-dismiss="alert">×</a>
      <p>${flash_message}</p> 
      <p></p> 
    </div>
</c:if>  