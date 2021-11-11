package cu.juego.implementacion.android

import android.view.MotionEvent
import android.view.View
import cu.juego.implementacion.Input.TouchEvent
import cu.juego.implementacion.Pool
import cu.juego.implementacion.Pool.PoolObjectFactory
import cu.juego.implementacion.TouchHandler
import java.util.*

class MultiTouchHandler(view: View, scaleX: Float, scaleY: Float) : TouchHandler {
    var isTouch = BooleanArray(20)
    var touchX = IntArray(20)
    var touchY = IntArray(20)
    var touchEventPool: Pool<TouchEvent>
    var touchEventBuffer: MutableList<TouchEvent> = ArrayList()
    var touchEvents: MutableList<TouchEvent> = ArrayList()
    var scaleX: Float
    var scaleY: Float
    fun onTouch(v: View?, event: MotionEvent): Boolean {
        synchronized(this) {
            val action: Int = event.getAction() and MotionEvent.ACTION_MASK
            var pointerIndex: Int = event.getAction() and MotionEvent.ACTION_MASK shr MotionEvent.ACTION_POINTER_ID_SHIFT
            var pointerId: Int = event.getPointerId(pointerIndex)
            var touchEvent: TouchEvent
            when (action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    touchEvent = touchEventPool.newObject()
                    touchEvent.type = TouchEvent.TOUCH_DOWN
                    touchEvent.pointer = pointerId
                    run {
                        touchX[pointerId] = (event.getX(pointerIndex) * scaleX) as Int
                        touchEvent.x = touchX[pointerId]
                    }
                    run {
                        touchX[pointerId] = (event.getY(pointerIndex) * scaleY) as Int
                        touchEvent.y = touchX[pointerId]
                    }
                    isTouch[pointerId] = true
                    touchEventBuffer.add(touchEvent)
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    touchEvent = touchEventPool.newObject()
                    touchEvent.type = TouchEvent.TOUCH_UP
                    touchEvent.pointer = pointerId
                    run {
                        touchX[pointerId] = (event.getX(pointerIndex) * scaleX) as Int
                        touchEvent.x = touchX[pointerId]
                    }
                    run {
                        touchX[pointerId] = (event.getY(pointerIndex) * scaleY) as Int
                        touchEvent.y = touchX[pointerId]
                    }
                    isTouch[pointerId] = true
                    touchEventBuffer.add(touchEvent)
                }
                MotionEvent.ACTION_MOVE -> {
                    val pointerCount: Int = event.getPointerCount()
                    var i = 0
                    while (i < pointerCount) {
                        pointerIndex = i
                        pointerId = event.getPointerId(pointerIndex)
                        touchEvent = touchEventPool.newObject()
                        touchEvent.type = TouchEvent.TOUCH_DRAGGED
                        touchEvent.pointer = pointerId
                        touchX[pointerId] = (event.getX(pointerIndex) * scaleX) as Int
                        touchEvent.x = touchX[pointerId]
                        touchX[pointerId] = (event.getY(pointerIndex) * scaleY) as Int
                        touchEvent.y = touchX[pointerId]
                        isTouch[pointerId] = true
                        touchEventBuffer.add(touchEvent)
                        i++
                    }
                }
                else -> {
                }
            }

            //touchEvent.x = touchX = (int) (event.getX()*scaleX);
            //touchEvent.y = touchY = (int) (event.getY()*scaleY);
            //touchEventBuffer.add(touchEvent);
            return true
        }
    }

    override fun isTouchDown(pointer: Int): Boolean {
        synchronized(this) {
            return if (pointer < 0 || pointer > 20) {
                false
            } else {
                isTouch[pointer]
            }
        }
    }

    override fun getTouchX(pointer: Int): Int {
        synchronized(this) {
            return if (pointer < 0 || pointer > 20) {
                0
            } else {
                touchX[pointer]
            }
        }
    }

    override fun getTouchY(pointer: Int): Int {
        synchronized(this) {
            return if (pointer < 0 || pointer > 20) {
                0
            } else {
                touchY[pointer]
            }
        }
    }

    override fun getTouchEvents(): List<TouchEvent> {
        synchronized(this) {
            val len = touchEvents.size
            for (i in 0 until len) {
                touchEventPool.free(touchEvents[i])
            }
            touchEvents.clear()
            touchEvents.addAll(touchEventBuffer)
            touchEventBuffer.clear()
            return touchEvents
        }
    }

    init {
        val factory: PoolObjectFactory<TouchEvent> = PoolObjectFactory<TouchEvent?> { // TODO Auto-generated method stub
            TouchEvent()
        }
        touchEventPool = Pool(factory, 100)
        view.setOnTouchListener(this)
        this.scaleX = scaleX
        this.scaleY = scaleY
    }
}