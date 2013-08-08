	function seleccionarTipo()
    {        
	    var idTipo = $("select[name*='idTipo']").val();
		    if(idTipo==1){ 
		        $(".clsBotonUsuario").attr('style','display:visible');
		        $(".clsBotonPerfil").attr('style','display:none');
		        $(".clsRestoUsuario").attr('style','display:visible');
		        }
	        if(idTipo==2){ 
	            $(".clsBotonUsuario").attr('style','display:none');
	            $(".clsBotonPerfil").attr('style','display:visible');
	            $(".clsRestoUsuario").attr('style','display:visible');
	            }
    }
	
	function Tipo2(){
    	$(".botonAtendido").attr('style','background-color: #ffc600');
    	$(".botonPendiente").attr('style','background-color: #fed02e');
    	$("#idTipo").attr('value','xxxx');
	}
	
	function Tipo1(){
		$(".botonAtendido").attr('style','background-color: #fed02e');
		$(".botonPendiente").attr('style','background-color: #ffc600');
	}
	
	function ocultar(){
		$(".clsUno").attr('style','display:none');
		$(".clsDos").attr('style','display:visible');
	}
	
	function ocultar2(){
		$(".clsUno").attr('style','display:visible');
		$(".clsDos").attr('style','display:none');
	}
		
	function EstadoNotificacion(){
		var idTip = $("select[name*='idTipo']").val();
		

	    if(idTip==1){ 
	    	
	    	$(".clsRevisado").attr('style','display:visible');
	    	$(".clsCancelado").attr('style','display:visible');

	    }else{
	        if(idTip==2){
	        	$(".clsRevisado").attr('style','display:none');
	        	$(".clsCancelado").attr('style','display:none');


	        }else{
	        	if(idTip==3){
		        	$(".clsRevisado").attr('style','display:visible');
		        	$(".clsCancelado").attr('style','display:none');
	
		        }else{
	        	
	        	$(".clsRevisado").attr('style','display:visible');
	        	$(".clsCancelado").attr('style','display:visible');
		        }
	        }
	    	
	    }
	}
	
	function openUploadFileDialogue(){
		
		$('input[type=file]').click();
	}
